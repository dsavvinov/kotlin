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

package org.jetbrains.kotlin.effectsystem.interpreting

import org.jetbrains.kotlin.descriptors.contracts.BooleanExpression
import org.jetbrains.kotlin.descriptors.contracts.ContractDescriptor
import org.jetbrains.kotlin.descriptors.contracts.EffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.expressions.ConstantDescriptor
import org.jetbrains.kotlin.descriptors.contracts.expressions.VariableReference
import org.jetbrains.kotlin.effectsystem.adapters.ValueIdsFactory
import org.jetbrains.kotlin.effectsystem.factories.createVariable
import org.jetbrains.kotlin.effectsystem.impls.ESConstant
import org.jetbrains.kotlin.effectsystem.impls.ESVariable
import org.jetbrains.kotlin.effectsystem.impls.EffectSchemaImpl
import org.jetbrains.kotlin.effectsystem.interpreting.effects.ConditionalEffectInterpreter
import org.jetbrains.kotlin.effectsystem.interpreting.effects.ReturnsEffectInterpreter
import org.jetbrains.kotlin.effectsystem.structure.ESBooleanExpression
import org.jetbrains.kotlin.effectsystem.structure.ESEffect
import org.jetbrains.kotlin.effectsystem.structure.ESFunctor
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.resolve.calls.smartcasts.IdentifierInfo

class ContractInterpretationDispatcher {
    private val constantsInterpreter = ConstantValuesInterpreter()
    private val conditionInterpreter = ConditionInterpreter(this)
    private val conditionalEffectInterpreter = ConditionalEffectInterpreter(this)
    private val effectsInterpreters = listOf<EffectDeclarationInterpreter>(
        ReturnsEffectInterpreter(this)
    )

    fun convertContractDescriptorToFunctor(contractDescriptor: ContractDescriptor): ESFunctor? {
        return sch
    }

    internal fun interpretEffect(effectDeclaration: EffectDeclaration): ESEffect? {
        val convertedFunctors = effectsInterpreters.mapNotNull { it.tryInterpret(effectDeclaration) }
        return convertedFunctors.singleOrNull()
    }

    internal fun interpretConstant(constantDescriptor: ConstantDescriptor): ESConstant? =
            constantsInterpreter.interpretConstant(constantDescriptor)

    internal fun interpretCondition(booleanExpression: BooleanExpression): ESBooleanExpression? =
            booleanExpression.accept(conditionInterpreter, Unit)

    internal fun interpretVariable(variableReference: VariableReference): ESVariable? {
        // TODO: hack - it is not necessarily stable value, but we will never use its stability, so we don't really care
        val identifierInfo = IdentifierInfo.Variable(variableReference.descriptor, DataFlowValue.Kind.STABLE_VALUE, null)
        val dfv = DataFlowValue(identifierInfo, variableReference.descriptor.type)
        return createVariable(ValueIdsFactory.dfvBased(dfv), variableReference.descriptor.type)
    }
}