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

import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.effectsystem.factories.NOT_NULL_CONSTANT
import org.jetbrains.kotlin.effectsystem.factories.NULL_CONSTANT
import org.jetbrains.kotlin.effectsystem.factories.UNKNOWN_CONSTANT
import org.jetbrains.kotlin.effectsystem.factories.lift
import org.jetbrains.kotlin.effectsystem.impls.ESConstant
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe

private val CONTRACT_FQN = FqName("kotlin.effects.dsl.contract")
private val EFFECT_FQN = FqName("kotlin.effects.dsl.Effect")
private val IMPLIES_FQN = FqName("kotlin.effects.dsl.Effect.implies")

private val RETURNS_FQN = FqName("kotlin.effects.dsl.ContractBuilder.returns")
private val CALLED_IN_PLACE_FQN = FqName("kotlin.effects.dsl.ContractBuilder.calledInPlace")

private val CONSTANT_VALUE_ENUM_FQN = FqName("kotlin.effects.dsl.ConstantValue")

private val TRUE_SHORT_NAME = Name.identifier("TRUE")
private val FALSE_SHORT_NAME = Name.identifier("FALSE")
private val NULL_SHORT_NAME = Name.identifier("NULL")
private val NOT_NULL_SHORT_NAME = Name.identifier("NOT_NULL")
private val WILDCARD_SHORT_NAME = Name.identifier("WILDCARD")

fun CallableDescriptor.isContractCallDescriptor(): Boolean = this is FunctionDescriptor && this.fqNameSafe == CONTRACT_FQN

fun CallableDescriptor.isImpliesCallDescriptor(): Boolean = this is FunctionDescriptor && this.fqNameSafe == IMPLIES_FQN

fun CallableDescriptor.isReturnsEffectCallDescriptor(): Boolean = this is FunctionDescriptor && this.fqNameSafe == RETURNS_FQN

fun CallableDescriptor.isCalledInPlaceEffectDescriptor(): Boolean = this is FunctionDescriptor && this.fqNameSafe == CALLED_IN_PLACE_FQN

fun CallableDescriptor.isConstantValue(): Boolean = this.fqNameSafe.parent() == CONSTANT_VALUE_ENUM_FQN

fun CallableDescriptor.isEffect(): Boolean = this.returnType?.constructor?.declarationDescriptor?.fqNameSafe == EFFECT_FQN

fun CallableDescriptor.toESConstant(): ESConstant? = when (this.fqNameSafe.shortName()) {
    TRUE_SHORT_NAME -> true.lift()
    FALSE_SHORT_NAME -> false.lift()
    NULL_SHORT_NAME -> NULL_CONSTANT
    NOT_NULL_SHORT_NAME -> NOT_NULL_CONSTANT
    WILDCARD_SHORT_NAME -> UNKNOWN_CONSTANT
    else -> null
}