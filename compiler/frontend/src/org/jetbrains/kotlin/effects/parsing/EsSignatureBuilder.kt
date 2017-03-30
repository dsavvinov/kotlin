/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.effects.parsing

import org.antlr.v4.runtime.tree.TerminalNode
import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.effects.facade.EsResolutionUtils
import org.jetbrains.kotlin.effects.parsing.antlr.EffectSystemBaseVisitor
import org.jetbrains.kotlin.effects.parsing.antlr.EffectSystemParser
import org.jetbrains.kotlin.effects.structure.effects.EsCalls
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsFunction
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.Cons
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.Nil
import org.jetbrains.kotlin.effects.structure.schema.NodeSequence
import org.jetbrains.kotlin.effects.structure.schema.operators.*
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.DelegatingBindingTrace
import org.jetbrains.kotlin.resolve.TemporaryBindingTrace
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValueFactory
import org.jetbrains.kotlin.resolve.calls.smartcasts.IdentifierInfo
import org.jetbrains.kotlin.types.KotlinType


typealias BinaryOperatorT<T> = (T, T) -> T
typealias UnaryOperatorT<T> = (T) -> T


class EsSignatureBuilder(val ownerDescriptor: CallableDescriptor, val esResolutionUtils: EsResolutionUtils) : EffectSystemBaseVisitor<EsNode?>() {
    override fun visitEffectSchema(ctx: EffectSystemParser.EffectSchemaContext): EffectSchema {
        val esClauses = ctx.clause().map(this::visitClause)

        return EffectSchema(esClauses)
    }

    override fun visitClause(ctx: EffectSystemParser.ClauseContext): Imply {
        val lhs = visitExpression(ctx.expression())
        val rhs = visitEffectsList(ctx.effectsList())

        return Imply(lhs, rhs)
    }

    override fun visitExpression(ctx: EffectSystemParser.ExpressionContext): EsNode =
            ctx.conjunction().map(this::visitConjunction).reduce(::EsOr)

    override fun visitConjunction(ctx: EffectSystemParser.ConjunctionContext): EsNode =
            ctx.equalityComparison().map(this::visitEqualityComparison).reduce(::EsAnd)

    override fun visitEqualityComparison(ctx: EffectSystemParser.EqualityComparisonContext): EsNode {
        val comparisons = ctx.comparison().map(this::visitComparison)
        val joiners = ctx.equalityOperator().map<EffectSystemParser.EqualityOperatorContext, BinaryOperatorT<EsNode>> {
            when ((it.getChild(0) as TerminalNode).symbol.type) {
                EffectSystemParser.EQEQ -> { x, y -> EsEqual(x, y) }
                EffectSystemParser.EXCLEQ -> { x, y -> EsNot(EsEqual(x, y)) }
                else -> throw IllegalArgumentException("Unexpected equality operator: ${it.text}")
            }
        }
        return comparisons.intercalate(joiners)
    }

    override fun visitComparison(ctx: EffectSystemParser.ComparisonContext): EsNode {
        val namedInfixes = ctx.namedInfix().map(this::visitNamedInfix)
        val joiners = ctx.comparisonOperator().map {
            when ((it.getChild(0) as TerminalNode).symbol.type) {
                EffectSystemParser.GEQ -> TODO()
                EffectSystemParser.GT -> TODO()
                EffectSystemParser.LEQ -> TODO()
                EffectSystemParser.LT -> TODO()
                else -> throw IllegalArgumentException("Unexpected comparison operator: ${it.text}")
            }
        }
        return namedInfixes.intercalate(joiners)
    }

    override fun visitNamedInfix(ctx: EffectSystemParser.NamedInfixContext): EsNode {
        val additiveExpressions = ctx.additiveExpression().map(this::visitAdditiveExpression)
        if (ctx.isOperation != null) {
            val rhsType = resolveType(ctx.type().SimpleName())
            assert(additiveExpressions.size == 1, { "IsExpression: Expected 1 additive expression, got ${additiveExpressions.size}" })
            return EsIs(additiveExpressions[0], rhsType)
        }

        if (ctx.inOperation().isNotEmpty()) {
            TODO()
        }

        assert(additiveExpressions.size == 1, { "No infix expression: Expected 1 additive expression, got ${additiveExpressions.size}" })
        return additiveExpressions[0]
    }

    override fun visitAdditiveExpression(ctx: EffectSystemParser.AdditiveExpressionContext): EsNode {
        val multiplicativeExpressions = ctx.multiplicativeExpression().map(this::visitMultiplicativeExpression)
        val operators = ctx.additiveOperator().map {
            when ((it.getChild(0) as TerminalNode).symbol.type) {
                EffectSystemParser.PLUS -> TODO()
                EffectSystemParser.MINUS -> TODO()
                else -> throw IllegalArgumentException("Unexpected additive operator: ${it.text}")
            }
        }

        return multiplicativeExpressions.intercalate(operators)
    }

    override fun visitMultiplicativeExpression(ctx: EffectSystemParser.MultiplicativeExpressionContext): EsNode {
        val unaryExpressions = ctx.prefixUnaryExpression().map(this::visitPrefixUnaryExpression)
        val operators = ctx.multiplicativeOperator().map {
            when ((it.getChild(0) as TerminalNode).symbol.type) {
                EffectSystemParser.MUL -> TODO()
                EffectSystemParser.DIV -> TODO()
                EffectSystemParser.PERC -> TODO()
                else -> throw IllegalArgumentException("Unexpected multiplicative operator: ${it.text}")
            }
        }

        return unaryExpressions.intercalate(operators)
    }

    override fun visitPrefixUnaryExpression(ctx: EffectSystemParser.PrefixUnaryExpressionContext): EsNode {
        val postfixExpr = visitPostfixUnaryExpression(ctx.postfixUnaryExpression())
        val prefixOps = ctx.prefixUnaryOperation().map {
            when ((it.getChild(0) as TerminalNode).symbol.type) {
                EffectSystemParser.PLUS -> TODO()
                EffectSystemParser.MINUS -> TODO()
                EffectSystemParser.NOT -> ::EsNot
                EffectSystemParser.PLUSPLUS -> TODO()
                EffectSystemParser.MINUSMINUS -> TODO()
                else -> throw IllegalArgumentException("Unexpected prefix unary operator: ${it.text}")
            }
        }

        return prefixOps.foldRight(postfixExpr, { op, acc -> op(acc) })
    }

    override fun visitPostfixUnaryExpression(ctx: EffectSystemParser.PostfixUnaryExpressionContext): EsNode {
        val atom = visitAtomicExpression(ctx.atomicExpression())
        val postfixOps = ctx.postfixUnaryOperation().map {
            when ((it.getChild(0) as TerminalNode).symbol.type) {
                EffectSystemParser.PLUSPLUS -> TODO()
                EffectSystemParser.MINUSMINUS -> TODO()
                EffectSystemParser.EXCLEXCL -> TODO()
                else -> throw IllegalArgumentException("Unexpected postfix unary operator: ${it.text}")
            }
        }

        return postfixOps.foldRight<UnaryOperatorT<EsNode>, EsNode>(atom, { op, acc -> op(acc) })
    }

    override fun visitAtomicExpression(ctx: EffectSystemParser.AtomicExpressionContext): EsNode {
        if (ctx.SimpleName() != null) {
            return resolveVariable(ctx.SimpleName())
        }

        if (ctx.literalConstant() != null) {
            return visitLiteralConstant(ctx.literalConstant())
        }

        return visitExpression(ctx.expression()!!)
    }


    override fun visitEffectsList(ctx: EffectSystemParser.EffectsListContext): NodeSequence {
        val effects = ctx.effect().map(this::visitEffect)
        return effects.foldRight(Nil, ::Cons)
    }

    override fun visitEffect(ctx: EffectSystemParser.EffectContext): EsNode {
        if (ctx.callsEffect() != null) {
            return visitCallsEffect(ctx.callsEffect())
        }

        if (ctx.returnsEffect() != null) {
            return visitReturnsEffect(ctx.returnsEffect())
        }

        return visitThrowsEffect(ctx.throwsEffect())
    }

    override fun visitThrowsEffect(ctx: EffectSystemParser.ThrowsEffectContext): EsThrows = EsThrows(resolveType(ctx.SimpleName()))

    override fun visitReturnsEffect(ctx: EffectSystemParser.ReturnsEffectContext): EsReturns {
        val arg: EsNode = ctx.SimpleName()?.let(this::resolveVariable) ?: visitLiteralConstant(ctx.literalConstant())
        return EsReturns(arg)
    }

    override fun visitCallsEffect(ctx: EffectSystemParser.CallsEffectContext): EsCalls {
        val callable = resolveVariable(ctx.SimpleName())
        val times = ctx.IntegerLiteral().text.toInt()

        return EsCalls(mutableMapOf(callable to times))
    }

    override fun visitLiteralConstant(ctx: EffectSystemParser.LiteralConstantContext): EsConstant {
        val ktExpression = esResolutionUtils.psiFactory.createExpression(ctx.text)
        val value: Any?
        val type: KotlinType

        if (ctx.BooleanLiteral() != null) {
            value = ctx.BooleanLiteral().text.toBoolean()
            type = DefaultBuiltIns.Instance.booleanType
        }
        else if (ctx.IntegerLiteral() != null) {
            value = ctx.IntegerLiteral().text.toInt()
            type = DefaultBuiltIns.Instance.intType
        }
        else if (ctx.NullLiteral() != null) {
            value = null
            type = DefaultBuiltIns.Instance.nullableNothingType
        }
        else if (ctx.StringLiteral() != null) {
            value = ctx.StringLiteral().text.trim('"')
            type = DefaultBuiltIns.Instance.stringType
        }
        else if (ctx.UnitLiteral() != null) {
            value = Unit
            type = DefaultBuiltIns.Instance.unitType
        }
        else {
            throw IllegalStateException("Unknown Literal Constant: ${(ctx.getChild(0) as TerminalNode).text}")
        }

        val dataFlowValue = DataFlowValueFactory.createDataFlowValue(ktExpression, type, esResolutionUtils.context, esResolutionUtils.moduleDescriptor)
        return EsConstant(value, type, dataFlowValue)
    }

    /**
     * Result of intercalate is very similar to 'fold', but we use different operations for each
     * step instead of one and the same.
     *
     * Example: `[1, 2, 3, 4].intercalate([*, +, -]) results in `((1 * 2) + 3) - 4 = 1`
     */
    private fun <T> List<T>.intercalate(joiners: List<BinaryOperatorT<T>>): T {
        if (isEmpty()) throw IllegalArgumentException("Can't intercalate empty list")
        if (size != (joiners.size + 1)) throw IllegalArgumentException("Size of intercalated list is not equal to size of joiners + 1")

        if (size == 1) return this[0]

        var acc = joiners[0](this[0], this[1])
        for (i in 2..size - 1) {
            acc = joiners[i - 1](acc, this[i])
        }

        return acc
    }

    private fun resolveType(simpleName: TerminalNode): KotlinType {
        val ktTypeReference = esResolutionUtils.psiFactory.createType(simpleName.text)
        val type = esResolutionUtils.typeResolver?.resolveType(
                esResolutionUtils.resolutionContext.scope,
                ktTypeReference,
                TemporaryBindingTrace.create(esResolutionUtils.resolutionContext.trace, "ES-resolving"),
                /* checkBounds = */ false
        )
        return type!!
    }

    private fun resolveVariable(simpleName: TerminalNode): EsVariable {
        val name = Name.identifier(simpleName.text)

        val parameterDescriptor = ownerDescriptor.valueParameters.find { it.name == name }
                                  ?: throw IllegalStateException("Can't find value parameter with a given name $name")
        val idInfo = IdentifierInfo.Variable(parameterDescriptor, DataFlowValue.Kind.STABLE_VALUE, null)
        val dfv = DataFlowValue(idInfo, parameterDescriptor.type)

        return EsVariable(dfv)
    }
}