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

package org.jetbrains.kotlin.effectsystem.interpretation

import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor
import org.jetbrains.kotlin.descriptors.ValueDescriptor
import org.jetbrains.kotlin.descriptors.VariableDescriptor
import org.jetbrains.kotlin.descriptors.contracts.BooleanExpression
import org.jetbrains.kotlin.descriptors.contracts.ContractDescriptor
import org.jetbrains.kotlin.descriptors.contracts.EffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.effects.ConditionalEffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.expressions.ConstantDescriptor
import org.jetbrains.kotlin.descriptors.contracts.expressions.VariableReference
import org.jetbrains.kotlin.effectsystem.adapters.ValueIdsFactory
import org.jetbrains.kotlin.effectsystem.factories.*
import org.jetbrains.kotlin.effectsystem.impls.ESConstant
import org.jetbrains.kotlin.effectsystem.impls.ESVariable
import org.jetbrains.kotlin.effectsystem.interpretation.effects.CallsEffectInterpreter
import org.jetbrains.kotlin.effectsystem.interpretation.effects.ConditionalEffectInterpreter
import org.jetbrains.kotlin.effectsystem.interpretation.effects.ReturnsEffectInterpreter
import org.jetbrains.kotlin.effectsystem.structure.ESBooleanExpression
import org.jetbrains.kotlin.effectsystem.structure.ESEffect
import org.jetbrains.kotlin.effectsystem.structure.ESFunctor
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.resolve.calls.smartcasts.IdentifierInfo

/**
 * This class manages conversion of ContractDescriptor (which is a stable declarative representation of function contract)
 * to [ESFunctor], i.e. imperative description of how and which effects should be produced during the call.
 */
class ContractInterpretationDispatcher {
    private val constantsInterpreter = ConstantValuesInterpreter()
    private val conditionInterpreter = ConditionInterpreter(this)
    private val conditionalEffectInterpreter = ConditionalEffectInterpreter(this)
    private val effectsInterpreters: List<EffectDeclarationInterpreter> = listOf(
        ReturnsEffectInterpreter(this),
        CallsEffectInterpreter(this)
    )

    fun convertContractDescriptorToFunctor(contractDescriptor: ContractDescriptor): ESFunctor? {
        // TODO: rewrite when we will get rid of clauses
        val ownerFunction = contractDescriptor.ownerFunction
        val receiver = listOfNotNull(ownerFunction.dispatchReceiverParameter?.toESVariable(), ownerFunction.extensionReceiverParameter?.toESVariable())
        val parameters = receiver + ownerFunction.valueParameters.map { it.toESVariable() ?: return null }

        val resultingClauses = contractDescriptor.effects.map { effect ->
            if (effect is ConditionalEffectDeclaration) {
                conditionalEffectInterpreter.interpret(effect) ?: return null
            } else {
                val singleEffect = effectsInterpreters.mapNotNull { it.tryInterpret(effect) }.singleOrNull() ?: return null
                createClause(true.lift(), singleEffect)
            }
        }

        return schemaFromClauses(resultingClauses, parameters)
    }

    internal fun interpretEffect(effectDeclaration: EffectDeclaration): ESEffect? {
        val convertedFunctors = effectsInterpreters.mapNotNull { it.tryInterpret(effectDeclaration) }
        return convertedFunctors.singleOrNull()
    }

    internal fun interpretConstant(constantDescriptor: ConstantDescriptor): ESConstant? =
            constantsInterpreter.interpretConstant(constantDescriptor)

    internal fun interpretCondition(booleanExpression: BooleanExpression): ESBooleanExpression? =
            booleanExpression.accept(conditionInterpreter, Unit)

    internal fun interpretVariable(variableReference: VariableReference): ESVariable? = variableReference.descriptor.toESVariable()

    private fun ValueDescriptor.toESVariable(): ESVariable? {
        // TODO: hack - it is not necessarily stable value, but we will never use its stability, so we don't really care
        val identifierInfo = when(this) {
            is VariableDescriptor -> IdentifierInfo.Variable (this, DataFlowValue.Kind.STABLE_VALUE, null)
            is ReceiverParameterDescriptor -> IdentifierInfo.Receiver(this.value)
            else -> return null
        }
        val dfv = DataFlowValue(identifierInfo, this.type)
        return createVariable(ValueIdsFactory.dfvBased(dfv), this.type)
    }
}