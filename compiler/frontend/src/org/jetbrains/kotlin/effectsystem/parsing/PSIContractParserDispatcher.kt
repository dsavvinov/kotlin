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

package org.jetbrains.kotlin.effectsystem.parsing

import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.ParameterDescriptor
import org.jetbrains.kotlin.descriptors.contracts.BooleanExpression
import org.jetbrains.kotlin.descriptors.contracts.ContractDescriptor
import org.jetbrains.kotlin.descriptors.contracts.EffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.expressions.BooleanVariableReference
import org.jetbrains.kotlin.descriptors.contracts.expressions.ConstantDescriptor
import org.jetbrains.kotlin.descriptors.contracts.expressions.ContractDescriptionValue
import org.jetbrains.kotlin.descriptors.contracts.expressions.VariableReference
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.effectsystem.parsing.effects.PSICallsEffectParser
import org.jetbrains.kotlin.effectsystem.parsing.effects.PSIConditionalEffectParser
import org.jetbrains.kotlin.effectsystem.parsing.effects.PSIReturnsEffectParser
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtLambdaExpression
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall

internal class PSIContractParserDispatcher(val trace: BindingTrace, val contractParsingServices: ContractParsingServices) {
    private val conditionParser = PSIConditionParser(trace, this)
    private val constantParser = PSIConstantParser(trace)
    private val effectsParsers: List<PSIEffectParser> = listOf(
            PSIReturnsEffectParser(trace, this),
            PSICallsEffectParser(trace, this),
            PSIConditionalEffectParser(trace, this)
    )

    fun parseContract(expression: KtExpression?, ownerDescriptor: FunctionDescriptor): ContractDescriptor? {
        if (expression == null) return null
        if (!contractParsingServices.isContractDescriptionCall(expression, trace.bindingContext)) return null

        val resolvedCall = expression.getResolvedCall(trace.bindingContext)!! // Must be non-null due to 'isContractDescriptionCall' check

        val lambda = resolvedCall.firstArgumentAsExpressionOrNull() as? KtLambdaExpression ?: return null

        val effects = lambda.bodyExpression?.statements?.mapNotNull { parseEffect(it) } ?: return null

        if (effects.isEmpty()) return null

        if (effects.size > 1) {
            trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(expression, "multi-effect contracts are not supported yet"))
            return null
        }

        val singleEffect = effects.single()

        return ContractDescriptor(singleEffect, ownerDescriptor)
    }

    fun parseCondition(expression: KtExpression?): BooleanExpression? = expression?.accept(conditionParser, Unit)

    fun parseEffect(expression: KtExpression?): EffectDeclaration? {
        if (expression == null) return null
        val parsedEffects = effectsParsers.map { it.tryParseEffect(expression) }
        val successfulResults = parsedEffects.filterIsInstance<EffectParsingResult.Success>()
        val errorsAreReported = parsedEffects.any { it is EffectParsingResult.ParsedWithErrors }

        if (errorsAreReported) return null

        if (successfulResults.isEmpty()) {
            trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(expression, "Unrecognized effect"))
            return null
        }

        if (successfulResults.size > 1) {
            trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(expression, "Ambiguous effect parsing"))
            return null
        }

        return successfulResults.single().effectDeclaration
    }

    fun parseConstant(expression: KtExpression?): ConstantDescriptor? {
        if (expression == null) return null
        return expression.accept(constantParser, Unit)
    }

    fun parseVariable(expression: KtExpression?): VariableReference? {
        if (expression == null) return null
        val descriptor = expression.getResolvedCall(trace.bindingContext)?.resultingDescriptor ?: return null
        if (descriptor !is ParameterDescriptor) {
            trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(expression, "only references to parameters are allowed in contract description"))
            return null
        }
        return if (descriptor.type == DefaultBuiltIns.Instance.booleanType)
            BooleanVariableReference(descriptor)
        else
            VariableReference(descriptor, descriptor.type)
    }

    fun parseValue(expression: KtExpression?): ContractDescriptionValue? {
        val variable = parseVariable(expression)
        if (variable != null) return variable

        return parseConstant(expression)
    }
}