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
import org.jetbrains.kotlin.descriptors.ValueDescriptor
import org.jetbrains.kotlin.descriptors.VariableDescriptor
import org.jetbrains.kotlin.descriptors.contracts.BooleanExpression
import org.jetbrains.kotlin.descriptors.contracts.ContractDescriptor
import org.jetbrains.kotlin.descriptors.contracts.EffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.effects.ConditionalEffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.expressions.BooleanVariableReference
import org.jetbrains.kotlin.descriptors.contracts.expressions.ConstantDescriptor
import org.jetbrains.kotlin.descriptors.contracts.expressions.ContractDescriptionValue
import org.jetbrains.kotlin.descriptors.contracts.expressions.VariableReference
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.effectsystem.parsing.effects.PSICallsEffectParser
import org.jetbrains.kotlin.effectsystem.parsing.effects.PSIConditionalEffectParser
import org.jetbrains.kotlin.effectsystem.parsing.effects.PSIReturnsEffectParser
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall

class PSIContractParserDispatcher(val trace: BindingTrace) {
    private val conditionParser = PSIConditionParser(trace, this)
    private val constantParser = PSIConstantParser(trace)
    private val effectsParsers: List<PSIEffectParser> = listOf(
            PSIReturnsEffectParser(trace, this),
            PSICallsEffectParser(trace, this),
            PSIConditionalEffectParser(trace, this)
    )


    fun fastCheckIfContractPresent(function: KtFunction): Boolean {
        if (function !is KtNamedFunction || !function.isTopLevel || !function.hasBlockBody() || function.hasModifier(KtTokens.OPERATOR_KEYWORD)) return false
        val firstExpression = (function.bodyExpression as? KtBlockExpression)?.statements?.firstOrNull() ?: return false
        return isContractDescriptionCallFastCheck(firstExpression)
    }

    fun parseContract(expression: KtExpression?, trace: BindingTrace, ownerDescriptor: FunctionDescriptor): ContractDescriptor? {
        if (expression == null) return null
        if (!expression.isContractDescriptionCall(trace.bindingContext)) return null

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
        val parsedEffects = effectsParsers.mapNotNull { it.tryParseEffect(expression) }

        if (parsedEffects.isEmpty()) {
            trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(expression, "Unrecognized effect"))
            return null
        }

        if (parsedEffects.size > 1) {
            trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(expression, "Ambiguous effect parsing"))
            return null
        }

        return parsedEffects.single()
    }

    fun parseConstant(expression: KtExpression?): ConstantDescriptor? {
        if (expression == null) return null
        return expression.accept(constantParser, Unit)
    }

    fun parseVariable(expression: KtExpression?): VariableReference? {
        if (expression == null) return null
        val descriptor = expression.getResolvedCall(trace.bindingContext)?.resultingDescriptor as? ValueDescriptor ?: return null
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