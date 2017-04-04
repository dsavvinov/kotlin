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

package org.jetbrains.kotlin.effects.structure.general

import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.descriptors.VariableDescriptor
import org.jetbrains.kotlin.effects.structure.call.CallTreeVisitor
import org.jetbrains.kotlin.effects.structure.call.CtNode
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.Term
import org.jetbrains.kotlin.effects.structure.schema.operators.Imply
import org.jetbrains.kotlin.effects.visitors.helpers.toNodeSequence
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.resolve.calls.smartcasts.IdentifierInfo
import org.jetbrains.kotlin.types.KotlinType

interface EsNode {
    fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}

data class EsVariable(val value: DataFlowValue) : EsNode, CtNode, Term {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
    override fun <T> accept(visitor: CallTreeVisitor<T>): T = visitor.visit(this)

    override fun castToSchema(): EffectSchema = EffectSchema(listOf(Imply(true.lift(), EsReturns(this).toNodeSequence())))
}

class EsConstant(val value: Any?, val type: KotlinType, val dataFlowValue: DataFlowValue?) : EsNode, CtNode, Term {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
    override fun <T> accept(visitor: CallTreeVisitor<T>): T = visitor.visit(this)

    override fun castToSchema(): EffectSchema = EffectSchema(listOf(Imply(true.lift(), EsReturns(this).toNodeSequence())))

    override fun toString(): String {
        return value.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as EsConstant

        if (value != other.value) return false

        return true
    }
}

class EsFunction(val name: String, val returnType: KotlinType, val descriptor: CallableDescriptor? = null) {
    var schema: EffectSchema? = null
}