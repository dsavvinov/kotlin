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

package org.jetbrains.kotlin.descriptors.contracts.expressions

import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.descriptors.ValueDescriptor
import org.jetbrains.kotlin.descriptors.VariableDescriptor
import org.jetbrains.kotlin.descriptors.contracts.BooleanExpression
import org.jetbrains.kotlin.descriptors.contracts.ContractDescriptionElement
import org.jetbrains.kotlin.descriptors.contracts.ContractDescriptorVisitor
import org.jetbrains.kotlin.types.KotlinType

enum class Constants {
    TRUE,
    FALSE,
    NULL,
    NOT_NULL,
    WILDCARD
}

interface ContractDescriptionValue : ContractDescriptionElement {
    override fun <R, D> accept(contractDescriptorVisitor: ContractDescriptorVisitor<R, D>, data: D): R =
            contractDescriptorVisitor.visitValue(this, data)
}

open class ConstantDescriptor(val constantValue: Constants, val type: KotlinType) : ContractDescriptionValue {
    override fun <R, D> accept(contractDescriptorVisitor: ContractDescriptorVisitor<R, D>, data: D): R =
            contractDescriptorVisitor.visitConstantDescriptor(this, data)
}

class BooleanConstantDescriptor(value: Constants) : ConstantDescriptor(value, DefaultBuiltIns.Instance.booleanType), BooleanExpression {
    override fun <R, D> accept(contractDescriptorVisitor: ContractDescriptorVisitor<R, D>, data: D): R =
            contractDescriptorVisitor.visitBooleanConstantDescriptor(this, data)
}

open class VariableReference(val descriptor: ValueDescriptor, val type: KotlinType) : ContractDescriptionValue {
    override fun <R, D> accept(contractDescriptorVisitor: ContractDescriptorVisitor<R, D>, data: D) =
            contractDescriptorVisitor.visitVariableReference(this, data)
}

class BooleanVariableReference(descriptor: ValueDescriptor) : VariableReference(descriptor, DefaultBuiltIns.Instance.booleanType), BooleanExpression {
    override fun <R, D> accept(contractDescriptorVisitor: ContractDescriptorVisitor<R, D>, data: D): R =
            contractDescriptorVisitor.visitBooleanVariableReference(this, data)
}