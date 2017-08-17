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

package org.jetbrains.kotlin.effectsystem.resolving.dsl

import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.effectsystem.resolving.utility.toESVariable
import org.jetbrains.kotlin.effectsystem.structure.ESValue
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.scopes.receivers.ExpressionReceiver

class ContractDescriptionTreeBuilder(val trace: BindingTrace) : KtVisitor<ContractNode?, Unit>() {

    override fun visitKtElement(element: KtElement, data: Unit): ContractNode? {
        val resolvedCall = element.getResolvedCall(trace.bindingContext) ?: return null

        var receiverArgumentIfAny: ContractNode? = null

        val dispatchReceiver = resolvedCall.dispatchReceiver
        if (dispatchReceiver is ExpressionReceiver) {
            receiverArgumentIfAny = dispatchReceiver.expression.accept(this, data) ?: return null
        }

        val ordinaryArguments = resolvedCall.valueArguments.values.map {
            (it as ExpressionValueArgument).valueArgument?.getArgumentExpression()?.accept(this, data) ?: return null
        }

        val allArguments = listOfNotNull(receiverArgumentIfAny) + ordinaryArguments

        val descriptor = resolvedCall.resultingDescriptor

        return when {
            descriptor.isImpliesCallDescriptor() -> ContractClause(element, allArguments)

            descriptor.isEffect() -> ContractEffect(resolveEffect(descriptor, element) ?: return null, element, allArguments)

            descriptor.isContractCallDescriptor() -> ContractRoot(element, allArguments)

            descriptor is ValueParameterDescriptor -> ContractValue(descriptor.toESVariable(), element)

            descriptor.isConstantValue() -> ContractValue(descriptor.toESConstant() ?: return report(element, "unrecognized constant value"), element)

            else -> reportUnsupported(element)
        }
    }

    override fun visitParenthesizedExpression(expression: KtParenthesizedExpression, data: Unit): ContractNode? {
        val deparenthesized = KtPsiUtil.safeDeparenthesize(expression)
        return if (deparenthesized == expression) return null else deparenthesized.accept(this, data)
    }

    override fun visitLambdaExpression(expression: KtLambdaExpression, data: Unit): ContractNode? {
        val statements = expression.bodyExpression?.statements?.map { it.accept(this, data) ?: return null } ?: return null
        return ContractBlock(expression, statements)
    }

    override fun visitIsExpression(expression: KtIsExpression, data: Unit): ContractNode? {
        val arg = expression.leftHandSide.accept(this, data) ?: return null
        val typeReference = expression.typeReference ?: return null
        val type = trace[BindingContext.TYPE, typeReference] ?: return null
        val operatorType = if (expression.isNegated) OperatorType.NOT_IS else OperatorType.IS

        return ContractOperator(operatorType, expression, listOf(arg, ContractType(type, typeReference)))
    }

    private fun resolveEffect(descriptor: CallableDescriptor, reportOn: KtElement): EffectType? = when {
        descriptor.isReturnsEffectCallDescriptor() -> EffectType.RETURNS
        descriptor.isCalledInPlaceEffectDescriptor() -> EffectType.CALLED_IN_PLACE
        else -> report(reportOn, "unrecognized effect")
    }

    private fun report(element: KtElement, message: String): Nothing? {
        trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(element, message))
        return null
    }

    private fun reportUnsupported(element: KtElement): Nothing? {
        trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(element, "illegal construction in contract description"))
        return null
    }
}