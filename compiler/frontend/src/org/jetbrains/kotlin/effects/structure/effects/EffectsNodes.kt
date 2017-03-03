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

package org.jetbrains.kotlin.effects.structure.effects

import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.lift
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.Term
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.Imply
import org.jetbrains.kotlin.effects.visitors.helpers.toNodeSequence

/**
 * Special subclass of effects, that represents particular outcome of
 * the corresponding code block.
 */
interface Outcome : EsNode, Term {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
    override fun castToSchema(): EffectSchema = EffectSchema(listOf(Imply(true.lift(), this.toNodeSequence())))

    fun followsFrom(other: Outcome): Boolean

    fun isSuccessfull(): Boolean

    fun merge(other: Outcome?, binaryOperator: BinaryOperator): Outcome

}
