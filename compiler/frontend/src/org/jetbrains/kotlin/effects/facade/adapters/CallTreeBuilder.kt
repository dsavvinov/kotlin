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

import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.effects.facade.EsResolutionContext
import org.jetbrains.kotlin.effects.structure.call.*
import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsLambda
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValueFactory
import org.jetbrains.kotlin.resolve.constants.CompileTimeConstant
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.utils.ifEmpty


class CallTreeBuilder(val esResolutionContext: EsResolutionContext) : KtVisitor<CtNode?, Unit>() {
    private val callables: MutableSet<DataFlowValue> = mutableSetOf()

    fun buildCallTree(expression: KtExpression): CallTree? {
        return CallTree(expression.accept(this, Unit) ?: return null, callables)
    }

    override fun visitConstantExpression(expression: KtConstantExpression, data: Unit): CtNode? {
        val bindingContext = esResolutionContext.context
        val type: KotlinType = bindingContext.getType(expression) ?: return null
        val compileTimeConstant: CompileTimeConstant<*>
                = bindingContext.get(BindingContext.COMPILE_TIME_VALUE, expression) ?: return null
        return EsConstant(compileTimeConstant.getValue(type) ?: return null, type, expression.createDataFlowValue())
    }

    override fun visitSimpleNameExpression(expression: KtSimpleNameExpression, data: Unit): CtNode? =
        expression.createDataFlowValue()?.let { EsVariable(it) }


    override fun visitThrowExpression(expression: KtThrowExpression, data: Unit?): CtNode? {
        return EsThrows(expression.thrownExpression?.getType(esResolutionContext.context) ?: return null)
    }

    override fun visitUnaryExpression(expression: KtUnaryExpression, data: Unit): CtNode? {
        val argNode = expression.baseExpression?.accept(this, data) ?: return null
        if (expression.operationToken == KtTokens.EXCL) {
            return CtNot(argNode)
        } else {
            // TODO: other unary and throw?
            return null
        }
    }

    override fun visitBinaryExpression(expression: KtBinaryExpression, data: Unit): CtNode? {
        val leftNode = expression.left?.accept(this, data) ?: return null
        val rightNode = expression.right?.accept(this, data) ?: return null
        return when (expression.operationToken) {
            KtTokens.EQEQ -> CtEqual(leftNode, rightNode)
            KtTokens.EXCLEQ -> CtNot(CtEqual(leftNode, rightNode))
            KtTokens.ANDAND -> CtAnd(leftNode, rightNode)
            KtTokens.OROR -> CtOr(leftNode, rightNode)
            else -> return null // TODO: other binary and throw?
        }
    }

    override fun visitCallExpression(expression: KtCallExpression, data: Unit): CtNode? {
        val resolvedCall = expression.getResolvedCall(esResolutionContext.context) ?: return null

        // TODO: varargs and default args
        val argNodes = resolvedCall.valueArgumentsByIndex?.map {
             (it as? ExpressionValueArgument)?.valueArgument?.getArgumentExpression()?.accept(this, data) ?: return null
        } ?: return null

        return CtCall(resolvedCall, argNodes)
    }

    override fun visitReferenceExpression(expression: KtReferenceExpression, data: Unit?): CtNode? {
        return (expression as? KtNameReferenceExpression)?.createDataFlowValue()?.let { EsVariable(it) }
    }

    override fun visitIsExpression(expression: KtIsExpression, data: Unit): CtNode? {
        val leftNode = expression.leftHandSide.accept(this, data) ?: return null
        val rightType: KotlinType = esResolutionContext.context.get(BindingContext.TYPE, expression.typeReference) ?: return null
        return if (expression.isNegated) CtNot(CtIs(leftNode, rightType)) else CtIs(leftNode, rightType)
    }

    override fun visitStringTemplateExpression(expression: KtStringTemplateExpression, data: Unit): CtNode {
        val concatenatedString = expression.entries.map { it.text }.ifEmpty { listOf("") }.reduce { s, acc -> s + acc }
        val dataFlowValue = DataFlowValueFactory.createDataFlowValue(
                expression,
                DefaultBuiltIns.Instance.stringType,
                esResolutionContext.context,
                esResolutionContext.moduleDescriptor
        )
        return EsConstant(concatenatedString, DefaultBuiltIns.Instance.stringType, dataFlowValue)
    }

    override fun visitLambdaExpression(expression: KtLambdaExpression, data: Unit): CtNode? {
        val body = expression.bodyExpression?.statements?.let { if (it.size == 1) it[0].accept(this, data) else null}
            val dfv = expression.createDataFlowValue() ?: return null
        val descriptor = esResolutionContext.context.get(BindingContext.DECLARATION_TO_DESCRIPTOR, expression.functionLiteral)
                as? CallableDescriptor ?: return null
        callables += dfv

        return CtLambda(dfv, body, descriptor)
    }

    private fun KtExpression.createDataFlowValue() : DataFlowValue? {
        return DataFlowValueFactory.createDataFlowValue(
                expression = this,
                type = esResolutionContext.context.getType(this) ?: return null,
                bindingContext = esResolutionContext.context,
                containingDeclarationOrModule = esResolutionContext.moduleDescriptor
        )
    }
}

fun KtExpression.buildCallTree(resolutionContext: EsResolutionContext)
        = CallTreeBuilder(resolutionContext).buildCallTree(this)