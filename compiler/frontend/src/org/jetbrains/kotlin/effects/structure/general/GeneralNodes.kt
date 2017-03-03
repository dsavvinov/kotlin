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

import org.jetbrains.kotlin.effects.structure.schema.operators.Imply
import org.jetbrains.kotlin.effects.structure.call.CallTreeVisitor
import org.jetbrains.kotlin.effects.structure.call.CtNode
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.lift
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.Term
import org.jetbrains.kotlin.effects.visitors.helpers.toNodeSequence
import org.jetbrains.kotlin.types.KotlinType

interface EsNode {
    fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}

data class EsVariable(val reference: String, val type: EsType) : EsNode, CtNode, Term {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
    override fun <T> accept(visitor: CallTreeVisitor<T>): T = visitor.visit(this)

    override fun castToSchema(): EffectSchema = EffectSchema(listOf(Imply(true.lift(), EsReturns(this).toNodeSequence())))
}

data class EsConstant(val value: Any?, val type: EsType) : EsNode, CtNode, Term {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
    override fun <T> accept(visitor: CallTreeVisitor<T>): T = visitor.visit(this)

    override fun toString(): String {
        return value.toString()
    }

    override fun castToSchema(): EffectSchema = EffectSchema(listOf(Imply(true.lift(), EsReturns(this).toNodeSequence())))
}

// TODO: composition or inheritance? Depends on the real KtType, I think
// Inheritance pros:
//   - Get all kt-types functionality (like, upper-bounds, lower-bounds, lca, subtyping, etc)
data class EsType(val ktType: KotlinType) {
    override fun toString(): String {
        return ktType.toString()
    }
}

fun (KotlinType).lift() : EsType = EsType(this)

data class EsFunction(val name: String, val formalArgs: List<EsVariable>, val returnType: EsType) {
    val returnVar: EsVariable = EsVariable("return_$name", returnType)
    var schema: EffectSchema? = null
}