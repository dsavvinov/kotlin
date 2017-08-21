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
import org.jetbrains.kotlin.effectsystem.structure.ESFunctor
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall

class ContractResolver {
    fun hasContractFastCheck(function: KtNamedFunction, functionDescriptor: FunctionDescriptor): Boolean {
        if (!function.isTopLevel || !function.hasBlockBody() || functionDescriptor.isOperator) return false
        val firstExpression = (function.bodyExpression as? KtBlockExpression)?.statements?.firstOrNull() ?: return false
        return isContractDescriptionCallFastCheck(firstExpression)
    }

    fun resolveContract(expression: KtExpression?, trace: BindingTrace, ownerDescriptor: FunctionDescriptor): ESFunctor? {
        if (expression == null) return null
        if (!expression.isContractDescriptionCall(trace.bindingContext)) return null

        return ContractBuilder(trace).getContract(expression, ownerDescriptor)
    }

    private fun isContractDescriptionCallFastCheck(expression: KtExpression): Boolean {
        if (expression !is KtCallExpression) return false
        return expression.calleeExpression?.text == "contract"
    }

    private fun KtExpression.isContractDescriptionCall(context: BindingContext): Boolean {
        if (!isContractDescriptionCallFastCheck(this)) return false
        val resolvedCall = getResolvedCall(context) ?: return false

        return resolvedCall.resultingDescriptor.isContractCallDescriptor()
    }
}