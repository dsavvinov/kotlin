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
import org.jetbrains.kotlin.effects.facade.EsResolutionContext
import org.jetbrains.kotlin.effects.structure.call.CallTreeVisitor
import org.jetbrains.kotlin.effects.structure.call.CtNode
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.Term
import org.jetbrains.kotlin.effects.structure.schema.operators.Imply
import org.jetbrains.kotlin.effects.visitors.helpers.toNodeSequence
import org.jetbrains.kotlin.resolve.DelegatingBindingTrace
import org.jetbrains.kotlin.resolve.TemporaryBindingTrace
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.types.KotlinType

interface EsNode {
    fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}

open class EsVariable(val value: DataFlowValue, val surrogateId: String = "") : EsNode, CtNode, Term {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
    override fun <T> accept(visitor: CallTreeVisitor<T>): T = visitor.visit(this)

    override fun castToSchema(): EffectSchema = EffectSchema(listOf(Imply(true.lift(), EsReturns(this).toNodeSequence())))

    companion object {
        val RESULT = EsVariable(DataFlowValue.ERROR, "RESULT")
    }

    override fun toString(): String = value.identifierInfo.toString()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as EsVariable

        if (value != other.value) return false
        if (surrogateId != other.surrogateId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + surrogateId.hashCode()
        return result
    }


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

class EsCall(val callable: EsNode, val arguments: List<EsNode>) : EsNode {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}

interface EsType : EsNode {
    val type: KotlinType
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}

class EsSimpleType(override val type: KotlinType) : EsType {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}

class EsGenericType(val bareTypeName: String, val typeParameters: List<EsNode>, val resolutionContext: EsResolutionContext) : EsType {
    var isConstructed: Boolean = false

    override val type: KotlinType by lazy { reconstructGenericType() }

    private fun reconstructGenericType(): KotlinType {
        val types = typeParameters.map { (it as? EsType)?.type ?: throw IllegalStateException("Can't construct generic type: illegal type argument $it") }
        val ktTypeReference = resolutionContext.psiFactory.createType("$bareTypeName <${types.joinToString(", ")}>")
        val resolvedType = resolutionContext.typeResolver.resolveType(
                resolutionContext.lexicalScope, ktTypeReference,
                TemporaryBindingTrace.create(DelegatingBindingTrace(resolutionContext.context, "es-resolving"), "es-resolving"),
                false
        )
        isConstructed = true
        return resolvedType
    }

    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}

class EsLambda(val dataFlowValue: DataFlowValue, val bodySchema: EsNode?, val descriptor: CallableDescriptor) : EsVariable(dataFlowValue) {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}

