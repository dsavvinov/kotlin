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

import org.jetbrains.kotlin.descriptors.contracts.BooleanExpression
import org.jetbrains.kotlin.descriptors.contracts.expressions.BooleanConstantDescriptor
import org.jetbrains.kotlin.descriptors.contracts.expressions.BooleanVariableReference
import org.jetbrains.kotlin.descriptors.contracts.expressions.EqualsPredicate
import org.jetbrains.kotlin.descriptors.contracts.expressions.IsInstancePredicate
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall
import org.jetbrains.kotlin.resolve.scopes.receivers.ExpressionReceiver

class PSIConditionParser(val trace: BindingTrace, val dispatcher: PSIContractParserDispatcher) : KtVisitor<BooleanExpression?, Unit>() {
    override fun visitIsExpression(expression: KtIsExpression, data: Unit): BooleanExpression? {
        val variable = dispatcher.parseVariable(expression.leftHandSide) ?: return null
        val typeReference = expression.typeReference ?: return null
        val type = trace[BindingContext.TYPE, typeReference] ?: return null

        return IsInstancePredicate(variable, type, expression.isNegated)
    }

    override fun visitKtElement(element: KtElement, data: Unit): BooleanExpression? {
        val resolvedCall = element.getResolvedCall(trace.bindingContext)
        val descriptor = resolvedCall?.resultingDescriptor ?: return null

        // boolean variable
        val booleanVariable = dispatcher.parseVariable(element as? KtExpression) as? BooleanVariableReference
        if (booleanVariable != null) return booleanVariable

        // boolean constant (i.e. true/false or enum values)
        val booleanConstant = dispatcher.parseConstant(element as? KtExpression) as? BooleanConstantDescriptor
        if (booleanConstant != null) return booleanConstant

        // operator
        when {
            descriptor.isEqualsDescriptor() -> {
                val left = dispatcher.parseValue((resolvedCall.dispatchReceiver as? ExpressionReceiver)?.expression) ?: return null
                val right = dispatcher.parseValue(resolvedCall.firstArgumentAsExpressionOrNull()) ?: return null
                val isNegated = (element as? KtBinaryExpression)?.operationToken == KtTokens.EXCLEQ ?: false

                return EqualsPredicate(left, right, isNegated)
            }

            else -> {
                trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(element, "unsupported construction"))
                return null
            }
        }
    }

    override fun visitParenthesizedExpression(expression: KtParenthesizedExpression, data: Unit): BooleanExpression? =
            KtPsiUtil.deparenthesize(expression)?.accept(this, data)
}