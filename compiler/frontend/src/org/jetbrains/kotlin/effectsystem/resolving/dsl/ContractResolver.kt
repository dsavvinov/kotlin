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

import org.jetbrains.kotlin.effectsystem.structure.KtContract
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall

class ContractResolver {

    fun resolveContract(expression: KtExpression?, trace: BindingTrace): KtContract? {
        if (expression == null) return null
        if (!checkIfIsContractCall(expression, trace.bindingContext)) return null

        val contractDescriptionTree = ContractDescriptionTreeBuilder(trace).let { expression.accept(it, Unit) } ?: return null

        assert(contractDescriptionTree is ContractRoot) {
            "Internal error: ContractDescriptionTree doesn't start with ContractRoot, but call was recognized as 'contract'"
        }
        TODO()
    }

    /** Should be as fast as possible */
    private fun checkIfIsContractCall(expression: KtExpression?, context: BindingContext): Boolean {
        if (expression !is KtCallExpression) return false
        val resolvedCall = expression.getResolvedCall(context) ?: return false

        return resolvedCall.resultingDescriptor.isContractCallDescriptor()
    }
}