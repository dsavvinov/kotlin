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
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslNames.CALLS_IN_PLACE
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslNames.CONTRACT
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslNames.CONTRACTS_DSL_ANNOTATION_FQN
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslNames.EFFECT
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslNames.EQUALS_NAME
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslNames.IMPLIES
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslNames.INVOCATION_KIND_ENUM
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslNames.RETURNS
import org.jetbrains.kotlin.effectsystem.parsing.ContractsDslNames.RETURNS_NOT_NULL
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.types.typeUtil.isBoolean
import org.jetbrains.kotlin.types.typeUtil.isNullableAny
import org.jetbrains.kotlin.utils.addToStdlib.safeAs


object ContractsDslNames {
    val CONTRACTS_DSL_ANNOTATION_FQN = FqName("kotlin.annotation.ContractsDSL")

    val CONTRACT = Name.identifier("contract")
    val EFFECT = Name.identifier("Effect")
    val IMPLIES = Name.identifier("implies")

    val RETURNS = Name.identifier("returns")
    val RETURNS_NOT_NULL = Name.identifier("returnsNotNull")
    val CALLS_IN_PLACE = Name.identifier("callsInPlace")

    val EQUALS_NAME = Name.identifier("equals")

    val INVOCATION_KIND_ENUM = Name.identifier("InvocationKind")
    val EXACTLY_ONCE_KIND = Name.identifier("EXACTLY_ONCE")
    val AT_LEAST_ONCE_KIND = Name.identifier("AT_LEAST_ONCE")
    val UNKNOWN_KIND = Name.identifier("UNKNOWN")
    val AT_MOST_ONCE_KIND = Name.identifier("AT_MOST_ONCE")
}

fun DeclarationDescriptor.isFromContractsDSL(): Boolean = this.annotations.hasAnnotation(CONTRACTS_DSL_ANNOTATION_FQN)

fun DeclarationDescriptor.isContractCallDescriptor(): Boolean = equalsDSLDescriptor(CONTRACT)

fun DeclarationDescriptor.isImpliesCallDescriptor(): Boolean = equalsDSLDescriptor(IMPLIES)

fun DeclarationDescriptor.isReturnsEffectDescriptor(): Boolean = equalsDSLDescriptor(RETURNS)

fun DeclarationDescriptor.isReturnsNotNullDescriptor(): Boolean = equalsDSLDescriptor(RETURNS_NOT_NULL)

fun DeclarationDescriptor.isEffectDescriptor(): Boolean = equalsDSLDescriptor(EFFECT)

fun DeclarationDescriptor.isCallsInPlaceEffectDescriptor(): Boolean = equalsDSLDescriptor(CALLS_IN_PLACE)

fun DeclarationDescriptor.isInvocationKindEnum(): Boolean = equalsDSLDescriptor(INVOCATION_KIND_ENUM)

fun DeclarationDescriptor.isEqualsDescriptor(): Boolean =
        this is FunctionDescriptor && this.name == EQUALS_NAME && // fast checks
        this.returnType?.isBoolean() == true && this.valueParameters.singleOrNull()?.type?.isNullableAny() == true // signature matches

fun DeclarationDescriptor.isEffectDeclarationCall(): Boolean =
        this is CallableDescriptor && this.returnType?.constructor?.declarationDescriptor?.isEffectDescriptor() ?: false

internal fun ResolvedCall<*>.firstArgumentAsExpressionOrNull(): KtExpression? =
        this.valueArgumentsByIndex?.firstOrNull()?.safeAs<ExpressionValueArgument>()?.valueArgument?.getArgumentExpression()

private fun DeclarationDescriptor.equalsDSLDescriptor(dslName: Name): Boolean = this.name == dslName && this.isFromContractsDSL()