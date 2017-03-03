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

package org.jetbrains.kotlin.effects.structure.schema

import org.jetbrains.kotlin.effects.structure.effects.EffectsPipelineFlags
import org.jetbrains.kotlin.effects.structure.schema.operators.Imply
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator

/**
 * General type of any side-effects that any computation may have.
 */
interface Effect : EsNode {
    /**
     * Returns result of combination of `this`-effect with `right`-Effects.
     *
     * `this` guaranteed to be present in `left`.
     *
     * This is most general signature of `merge()`-function. See `SimpleEffect`
     * for more convenient and special case.
     */
    fun merge(left: List<Effect>, right: List<Effect>, flags: EffectsPipelineFlags, operator: BinaryOperator) : List<Effect>
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}

/**
 * General type of an operation that knows how to flatten if one of its arguemnts is EffectSchema
 */
interface Operator : EsNode {
    /**
     * Merges arguments in EffectSchema if at least one of them was EffectSchema.
     * Otherwise, returns `this` unchanged
     */
    fun flatten(): EsNode

    /**
     * Tries to evaluate operator on its arguments, returning `this` unchanged if evaluation is impossible.
     */
    fun reduce(): EsNode

    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}

/**
 * Term is an irreducible EsNode. All terms should be equivalent to some form of EffectSchema
 */
interface Term : EsNode {
    fun castToSchema(): EffectSchema
}

data class EffectSchema(val clauses: List<Imply>) : Term {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
    override fun castToSchema(): EffectSchema = this
}

