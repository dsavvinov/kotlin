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

import org.jetbrains.kotlin.descriptors.FunctionDescriptor

/**
 * This is actual model of contracts, i.e. what is expected to be parsed from
 * general protobuf format.
 *
 * Its intention is to provide declarative representation which is more stable
 * than inner representation of effect system, while enforcing type-checking which
 * isn't possible in protobuf representation.
 *
 * Any changes to this model should be done with previous versions in mind to keep
 * backward compatibility. Ideally, this model should only be extended, but not
 * changed.
 */
open class ContractDescriptor(val effect: EffectDeclaration, val ownerFunction: FunctionDescriptor)

interface ContractDescriptionElement {
    fun <R, D> accept(contractDescriptorVisitor: ContractDescriptorVisitor<R, D>, data: D): R
}

interface EffectDeclaration : ContractDescriptionElement {
    override fun <R, D> accept(contractDescriptorVisitor: ContractDescriptorVisitor<R, D>, data: D): R =
            contractDescriptorVisitor.visitEffectDeclaration(this, data)
}

interface BooleanExpression : ContractDescriptionElement {
    override fun <R, D> accept(contractDescriptorVisitor: ContractDescriptorVisitor<R, D>, data: D): R =
            contractDescriptorVisitor.visitBooleanExpression(this, data)
}