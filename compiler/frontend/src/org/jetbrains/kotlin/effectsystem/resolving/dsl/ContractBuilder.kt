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

import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.effectsystem.effects.ESCalls
import org.jetbrains.kotlin.effectsystem.effects.ESReturns
import org.jetbrains.kotlin.effectsystem.effects.InvocationKind
import org.jetbrains.kotlin.effectsystem.factories.lift
import org.jetbrains.kotlin.effectsystem.impls.EffectSchemaImpl
import org.jetbrains.kotlin.effectsystem.structure.ESBooleanExpression
import org.jetbrains.kotlin.effectsystem.structure.ESClause
import org.jetbrains.kotlin.effectsystem.structure.ESEffect
import org.jetbrains.kotlin.effectsystem.structure.ESFunctor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ResolvedValueArgument
import org.jetbrains.kotlin.resolve.scopes.receivers.ExpressionReceiver
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

class ContractBuilder(val trace: BindingTrace) {
    private val conditionBuilder = ConditionBuilder(trace)
    private val constantsBuilder = ConstantsBuilder(trace)

    fun getContract(element: KtElement, ownerDescriptor: FunctionDescriptor): ESFunctor? {
        val resolvedCall = element.getResolvedCall(trace.bindingContext)
        val descriptor = resolvedCall?.resultingDescriptor ?: return null

        if (!descriptor.isContractCallDescriptor()) return null

        val firstArgument = resolvedCall.firstArgumentAsExpressionOrNull() as? KtLambdaExpression ?: return null

        val clauses = firstArgument.bodyExpression?.statements.orEmpty().mapNotNull { getClause(it) }

        val receiver = ownerDescriptor.extensionReceiverParameter?.receiverToESVariable()

        return EffectSchemaImpl(clauses, listOfNotNull(receiver) + ownerDescriptor.valueParameters.map { it.toESVariable() })
    }

    private fun getClause(expression: KtExpression): ESClause? {
        if (expression is KtParenthesizedExpression) return getClause(KtPsiUtil.deparenthesize(expression) ?: return null)

        val resolvedCall = expression.getResolvedCall(trace.bindingContext) ?: return null

        val effect: ESEffect
        val condition: ESBooleanExpression

        if (resolvedCall.resultingDescriptor.isImpliesCallDescriptor()) {
            effect = getEffect(resolvedCall.dispatchReceiver.safeAs<ExpressionReceiver>()?.expression) ?: return null
            condition = getCondition(resolvedCall.valueArguments.values.singleOrNull()) ?: return null
        } else {
            // Unconditional effect
            effect = getEffect(expression) ?: return report(expression, "unrecognized effect")
            condition = true.lift()
        }

        return ESClause(condition, effect)
    }

    private fun getCondition(argument: ResolvedValueArgument?): ESBooleanExpression? {
        val expression = (argument as? ExpressionValueArgument)?.valueArgument?.getArgumentExpression() ?: return null
        return expression.accept(conditionBuilder, Unit)
    }

    private fun getEffect(expression: KtExpression?): ESEffect? {
        if (expression == null) return null

        val resolvedCall = expression.getResolvedCall(trace.bindingContext) ?: return null
        val descriptor = resolvedCall.resultingDescriptor

        return when {
            descriptor.isReturnsEffectDescriptor() -> {
                val argumentExpression = resolvedCall.firstArgumentAsExpressionOrNull() ?: return null
                val constantValue = argumentExpression.accept(constantsBuilder, Unit) ?: return report(argumentExpression, "illegal contract-constant")
                ESReturns(constantValue)
            }

            descriptor.isCallsInPlaceEffectDescriptor() -> {
                val lambda = resolvedCall.firstArgumentAsExpressionOrNull()?.toESVariable(trace) ?: return null
                val kind = (resolvedCall.valueArgumentsByIndex?.getOrNull(1) as? ExpressionValueArgument)?.valueArgument
                        ?.getArgumentExpression()?.toInvocationKind(trace) ?: InvocationKind.UNKNOWN
                ESCalls(lambda, kind)
            }

            else -> report(expression, "unrecognized effect")
        }
    }

    private fun report(element: KtElement, message: String): Nothing? {
        trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(element, message))
        return null
    }
}