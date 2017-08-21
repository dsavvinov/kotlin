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

import org.jetbrains.kotlin.effectsystem.effects.ESCalls
import org.jetbrains.kotlin.effectsystem.effects.InvocationKind
import org.jetbrains.kotlin.effectsystem.resolving.dsl.*
import org.jetbrains.kotlin.effectsystem.structure.ESEffect
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.model.DefaultValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall

class CallsEffectBuilder(val trace: BindingTrace) : EffectBuilder {
    override fun tryParseEffect(resolvedCall: ResolvedCall<*>): ESEffect? {
        val descriptor = resolvedCall.resultingDescriptor

        if (!descriptor.isCallsInPlaceEffectDescriptor()) return null

        val lambda = resolvedCall.firstArgumentAsExpressionOrNull()?.toESVariable(trace) ?: return null

        val kindArgument = resolvedCall.valueArgumentsByIndex?.getOrNull(1)

        val kind = if (kindArgument is DefaultValueArgument) {
            InvocationKind.UNKNOWN
        } else {
            // Argument can't be parsed, don't default to unknown, return null instead to indicate some problems in description
            (kindArgument as? ExpressionValueArgument)?.valueArgument?.getArgumentExpression()?.toInvocationKind(trace) ?: return null
        }

        return ESCalls(lambda, kind)
    }
}