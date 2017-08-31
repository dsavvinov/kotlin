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

import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslFqns.CALLS_IN_PLACE_FQN
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslFqns.CONTRACT_FQN
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslFqns.EFFECT_FQN
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslFqns.EQUALS_NAME
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslFqns.IMPLIES_FQN
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslFqns.RETURNS_FQN
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslFqns.RETURNS_NOT_NULL_FQN
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.types.typeUtil.isBoolean
import org.jetbrains.kotlin.types.typeUtil.isNullableAny
import org.jetbrains.kotlin.utils.addToStdlib.safeAs


object ContractsDslFqns {
    val CONTRACT_FQN = FqName("kotlin.effects.dsl.contract")
    val EFFECT_FQN = FqName("kotlin.effects.dsl.Effect")
    val IMPLIES_FQN = FqName("kotlin.effects.dsl.Effect.implies")

    val RETURNS_FQN = FqName("kotlin.effects.dsl.ContractBuilder.returns")
    val RETURNS_NOT_NULL_FQN = FqName("kotlin.effects.dsl.ContractBuilder.returnsNotNull")
    val CALLS_IN_PLACE_FQN = FqName("kotlin.effects.dsl.ContractBuilder.callsInPlace")

    val EQUALS_NAME = Name.identifier("equals")

    val INVOCATION_KIND_FQN = FqName("kotlin.effects.dsl.InvocationKind")
    val EXACTLY_ONCE_NAME = Name.identifier("EXACTLY_ONCE")
    val AT_LEAST_ONCE_NAME = Name.identifier("AT_LEAST_ONCE")
    val UNKNOWN_NAME = Name.identifier("UNKNOWN")
    val AT_MOST_ONCE_NAME = Name.identifier("AT_MOST_ONCE")
}

fun CallableDescriptor.isContractCallDescriptor(): Boolean = this is FunctionDescriptor && this.fqNameSafe == CONTRACT_FQN

fun CallableDescriptor.isImpliesCallDescriptor(): Boolean = this is FunctionDescriptor && this.fqNameSafe == IMPLIES_FQN

fun CallableDescriptor.isReturnsEffectDescriptor(): Boolean = this is FunctionDescriptor && this.fqNameSafe == RETURNS_FQN

fun CallableDescriptor.isReturnsNotNullDescriptor(): Boolean = this is FunctionDescriptor && this.fqNameSafe == RETURNS_NOT_NULL_FQN

fun CallableDescriptor.isEqualsDescriptor(): Boolean =
        this is FunctionDescriptor && this.name == EQUALS_NAME && // fast checks
        this.returnType?.isBoolean() == true && this.valueParameters.singleOrNull()?.type?.isNullableAny() == true // signature matches

fun CallableDescriptor.isCallsInPlaceEffectDescriptor(): Boolean = this is FunctionDescriptor && this.fqNameSafe == CALLS_IN_PLACE_FQN

fun CallableDescriptor.isEffectCall(): Boolean = this.returnType?.constructor?.declarationDescriptor?.fqNameSafe == EFFECT_FQN

fun ResolvedCall<*>.firstArgumentAsExpressionOrNull(): KtExpression? =
        this.valueArgumentsByIndex?.firstOrNull()?.safeAs<ExpressionValueArgument>()?.valueArgument?.getArgumentExpression()
