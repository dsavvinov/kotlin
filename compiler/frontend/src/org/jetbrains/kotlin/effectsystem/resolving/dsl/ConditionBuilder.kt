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

import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.effectsystem.functors.IsFunctor
import org.jetbrains.kotlin.effectsystem.impls.ESBooleanConstant
import org.jetbrains.kotlin.effectsystem.impls.ESEqual
import org.jetbrains.kotlin.effectsystem.impls.ESIs
import org.jetbrains.kotlin.effectsystem.structure.ESBooleanExpression
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall
import org.jetbrains.kotlin.resolve.scopes.receivers.ExpressionReceiver
import org.jetbrains.kotlin.types.typeUtil.isBooleanOrNullableBoolean
import org.jetbrains.kotlin.types.typeUtil.makeNullable

class ConditionBuilder(val trace: BindingTrace) : KtVisitor<ESBooleanExpression?, Unit>() {
    private val constantsBuilder = ConstantsBuilder(trace)

    override fun visitIsExpression(expression: KtIsExpression, data: Unit): ESBooleanExpression? {
        val variable = expression.leftHandSide.toESVariable(trace) ?: return null
        val typeReference = expression.typeReference ?: return null
        val type = trace[BindingContext.TYPE, typeReference] ?: return null

        return ESIs(variable, IsFunctor(type, expression.isNegated))
    }

    override fun visitKtElement(element: KtElement, data: Unit): ESBooleanExpression? {
        val resolvedCall = element.getResolvedCall(trace.bindingContext)
        val descriptor = resolvedCall?.resultingDescriptor ?: return null

        // boolean variable
        if (descriptor is ValueParameterDescriptor && descriptor.returnType?.isBooleanOrNullableBoolean() == true) {
            return descriptor.toESBooleanVariable()
        }

        // boolean constant (i.e. true/false or enum values)
        val booleanConstant = element.accept(constantsBuilder, Unit) as? ESBooleanConstant
        if (booleanConstant != null) return booleanConstant

        // operator
        return when {
            descriptor.isEqualsDescriptor() -> {
                val value = (resolvedCall.dispatchReceiver as? ExpressionReceiver)?.expression?.toESVariable(trace) ?: return null
                val constant = resolvedCall.firstArgumentAsExpressionOrNull()?.accept(constantsBuilder, Unit) ?: return null
                val isAtMostBinaryType = value.type == DefaultBuiltIns.Instance.unitType ||
                                         value.type == DefaultBuiltIns.Instance.unitType.makeNullable() ||
                                         value.type == DefaultBuiltIns.Instance.booleanType
                val isNegated = (resolvedCall.call.callElement as? KtBinaryExpression)?.operationToken == KtTokens.EXCLEQ
                ESEqual(value, constant, isNegated, isAtMostBinaryType)
            }

            else -> {
                trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(element, "unsupported construction"))
                null
            }
        }
    }

    override fun visitParenthesizedExpression(expression: KtParenthesizedExpression, data: Unit): ESBooleanExpression? =
            KtPsiUtil.deparenthesize(expression)?.accept(this, data)
}