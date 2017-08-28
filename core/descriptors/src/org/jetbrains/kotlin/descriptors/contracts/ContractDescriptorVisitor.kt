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

package org.jetbrains.kotlin.descriptors.contracts

import org.jetbrains.kotlin.descriptors.contracts.effects.CallsEffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.effects.ConditionalEffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.effects.ReturnsEffectDeclaration
import org.jetbrains.kotlin.descriptors.contracts.expressions.*

interface ContractDescriptorVisitor<out R, in D> {
    // Effects
    fun visitEffectDeclaration(effectDeclaration: EffectDeclaration, data: D): R
    fun visitConditionalEffectDeclaration(conditionalEffectDescriptor: ConditionalEffectDeclaration, data: D): R
    fun visitReturnsEffectDeclaration(returnsEffectDescriptor: ReturnsEffectDeclaration, data: D): R
    fun visitCallsEffectDeclaration(callsEffectDescriptor: CallsEffectDeclaration, data: D): R

    // Expressions
    fun visitBooleanExpression(booleanExpression: BooleanExpression, data: D): R

    fun visitLogicalOr(logicalOr: LogicalOr, data: D): R
    fun visitLogicalAnd(logicalAnd: LogicalAnd, data: D): R
    fun visitLogicalNot(logicalNot: LogicalNot, data: D): R

    fun visitIsInstancePredicate(isInstancePredicate: IsInstancePredicate, data: D): R
    fun visitEqualsPredicate(equalsPredicate: EqualsPredicate, data: D): R

    // Values
    fun visitValue(value: ContractDescriptionValue, data: D): R

    fun visitConstantDescriptor(constantDescriptor: ConstantDescriptor, data: D): R
    fun visitBooleanConstantDescriptor(booleanConstantDescriptor: BooleanConstantDescriptor, data: D): R
    fun visitVariableReference(variableReference: VariableReference, data: D): R
    fun visitBooleanVariableReference(booleanVariableReference: BooleanVariableReference, data: D): R
}