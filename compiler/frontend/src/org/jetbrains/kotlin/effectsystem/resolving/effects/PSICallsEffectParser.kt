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

package org.jetbrains.kotlin.effectsystem.resolving.effects

import org.jetbrains.kotlin.descriptors.contracts.EffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.effects.CallsEffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.effects.InvocationKind
import org.jetbrains.kotlin.effectsystem.resolving.dsl.*
import org.jetbrains.kotlin.effectsystem.resolving.dsl.ContractsDslFqns.AT_LEAST_ONCE_NAME
import org.jetbrains.kotlin.effectsystem.resolving.dsl.ContractsDslFqns.AT_MOST_ONCE_NAME
import org.jetbrains.kotlin.effectsystem.resolving.dsl.ContractsDslFqns.EXACTLY_ONCE_NAME
import org.jetbrains.kotlin.effectsystem.resolving.dsl.ContractsDslFqns.INVOCATION_KIND_FQN
import org.jetbrains.kotlin.effectsystem.resolving.dsl.ContractsDslFqns.UNKNOWN_NAME
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.DefaultValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.resolve.descriptorUtil.parents

class PSICallsEffectParser(
        trace: BindingTrace,
        contractParserDispatcher: PSIContractParserDispatcher
) : AbstractPSIEffectParser(trace, contractParserDispatcher) {


    override fun tryParseEffect(expression: KtExpression): EffectDeclaration? {
        val resolvedCall = expression.getResolvedCall(trace.bindingContext) ?: return null
        val descriptor = resolvedCall.resultingDescriptor

        if (!descriptor.isCallsInPlaceEffectDescriptor()) return null

        val lambda = contractParserDispatcher.parseVariable(resolvedCall.firstArgumentAsExpressionOrNull()) ?: return null

        val kindArgument = resolvedCall.valueArgumentsByIndex?.getOrNull(1)

        val kind = if (kindArgument is DefaultValueArgument) {
            InvocationKind.UNKNOWN
        } else {
            // Argument can't be parsed, don't default to unknown, return null instead to indicate some problems in description
            (kindArgument as? ExpressionValueArgument)?.valueArgument?.getArgumentExpression()?.toInvocationKind(trace) ?: return null
        }

        return CallsEffectDeclaration(lambda, kind)
    }

    private fun KtExpression.toInvocationKind(trace: BindingTrace): InvocationKind? {
        val descriptor = this.getResolvedCall(trace.bindingContext)?.resultingDescriptor ?: return null
        if (descriptor.parents.first().fqNameSafe != INVOCATION_KIND_FQN) return null

        return when (descriptor.fqNameSafe.shortName()) {
            AT_MOST_ONCE_NAME -> InvocationKind.AT_MOST_ONCE
            EXACTLY_ONCE_NAME -> InvocationKind.EXACTLY_ONCE
            AT_LEAST_ONCE_NAME -> InvocationKind.AT_LEAST_ONCE
            UNKNOWN_NAME -> InvocationKind.UNKNOWN
            else -> null
        }
    }
}