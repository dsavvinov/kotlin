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

package org.jetbrains.kotlin.effectsystem.parsing.effects

import org.jetbrains.kotlin.descriptors.contracts.effects.ReturnsEffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.expressions.ConstantDescriptor
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.effectsystem.parsing.*
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall

internal class PSIReturnsEffectParser(
        trace: BindingTrace,
        contractParserDispatcher: PSIContractParserDispatcher
) : AbstractPSIEffectParser(trace, contractParserDispatcher) {
    override fun tryParseEffect(expression: KtExpression): EffectParsingResult {
        val resolvedCall = expression.getResolvedCall(trace.bindingContext) ?: return EffectParsingResult.UNRECOGNIZED
        val descriptor = resolvedCall.resultingDescriptor

        // TODO: relax assertion
        if (descriptor.isReturnsNotNullDescriptor())
            return EffectParsingResult.Success(ReturnsEffectDeclaration(ConstantDescriptor.NOT_NULL))

        if (!descriptor.isReturnsEffectDescriptor()) return EffectParsingResult.UNRECOGNIZED

        val argumentExpression = resolvedCall.firstArgumentAsExpressionOrNull()
        val constantValue = if (argumentExpression == null)
            ConstantDescriptor.WILDCARD
        else {
            // Note that we distinguish absence of an argument and unparsed argument
            val constant = contractParserDispatcher.parseConstant(argumentExpression)
            if (constant == null) {
                trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(argumentExpression, "only true/false/null constants in Returns-effect are currently supported"))
                return EffectParsingResult.PARSED_WITH_ERRORS
            }
            constant
        }

        return EffectParsingResult.Success(ReturnsEffectDeclaration(constantValue))
    }
}