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

package org.jetbrains.kotlin.effects.facade.adapters

import org.jetbrains.kotlin.effects.structure.*
import org.jetbrains.kotlin.effects.structure.call.CtCall
import org.jetbrains.kotlin.effects.structure.call.CtNode
import org.jetbrains.kotlin.effects.structure.general.*
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.constants.CompileTimeConstant
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.utils.ifEmpty

class CallTreeBuilder(val context: BindingContext) : KtVisitor<CtNode, Nothing?>() {

    fun buildCallTree(call: ResolvedCall<*>): CtNode? {
            return call.call.callElement.accept(this, null)
    }

    override fun visitConstantExpression(expression: KtConstantExpression, data: Nothing?): CtNode? {
        val type: KotlinType = context.getType(expression) ?: return null
        val compileTimeConstant: CompileTimeConstant<*>
                = context.get(BindingContext.COMPILE_TIME_VALUE, expression) ?: return null
        return EsConstant(compileTimeConstant.getValue(type) ?: return null, type.lift())
    }

    override fun visitSimpleNameExpression(expression: KtSimpleNameExpression, data: Nothing?): CtNode? {
        val type: KotlinType = context.getType(expression) ?: return null
        return EsVariable(expression.getReferencedName(), type.lift())
    }

    override fun visitUnaryExpression(expression: KtUnaryExpression, data: Nothing?): CtNode? {
        val argNode = expression.baseExpression?.accept(this, data) ?: return null
        if (expression.operationToken == KtTokens.EXCL) {
            return CtCall(NotFunction, listOf(argNode))
        } else {
            // TODO: other unary and throw?
            return null
        }
    }

    override fun visitBinaryExpression(expression: KtBinaryExpression, data: Nothing?): CtNode? {
        val leftNode = expression.left?.accept(this, data) ?: return null
        val rightNode = expression.right?.accept(this, data) ?: return null
        val operatorFunction =
                when (expression.operationToken) {
                    KtTokens.EQEQ -> eqFunction
                    KtTokens.EXCLEQ -> neqFunction
                    else -> return null // TODO: other binary and throw?
                }
        return CtCall(operatorFunction, listOf(leftNode, rightNode))
    }

    override fun visitCallExpression(expression: KtCallExpression, data: Nothing?): CtNode? {
        val resolvedCall = expression.getResolvedCall(context) ?: return null

        // TODO: varargs and default args
        val argNodes = resolvedCall.valueArgumentsByIndex?.map {
            (it as? ExpressionValueArgument)?.valueArgument?.getArgumentExpression()?.accept(this, data) ?: return null
        } ?: return null

        // TODO: totally temporary!
        val function: EsFunction = EsFunction(
                resolvedCall.resultingDescriptor.name.identifier,
                resolvedCall.resultingDescriptor.valueParameters.map { EsVariable(it.name.identifier, it.type.lift()) },
                resolvedCall.resultingDescriptor.returnType?.lift() ?: return null
        )

        return CtCall(function, argNodes)
    }

    override fun visitIsExpression(expression: KtIsExpression, data: Nothing?): CtNode? {
        val leftNode: CtNode = expression.leftHandSide.accept(this, data)
        val rightType: EsType = context.get(BindingContext.TYPE, expression.typeReference)?.lift() ?: return null
        val function = if (expression.isNegated) notIsFunction else isFunction

        return CtCall(function, listOf(leftNode, rightType))
    }

    override fun visitStringTemplateExpression(expression: KtStringTemplateExpression, data: Nothing?): CtNode {
        return expression.entries.map { it.text }.ifEmpty { listOf("") }.reduce { s, acc -> s + acc }.lift()
    }
}