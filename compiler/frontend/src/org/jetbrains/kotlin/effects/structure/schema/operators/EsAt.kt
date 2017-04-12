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

package org.jetbrains.kotlin.effects.structure.schema.operators

import org.jetbrains.kotlin.effects.structure.effects.Outcome
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.visitors.helpers.getOutcome

/**
 * This operator expects EffectSchema at `lhs` and some Outcome at `rhs`. Result of
 * flattening of this operator is EffectSchema, whose clauses are subset of `lhs` and
 * their Outcome follows from `rhs`.
 */
class EsAt(override val left: EsNode, override val right: EsNode) : BinaryOperator {
    override fun reduce(): EsNode {
        throw IllegalStateException("At-operator shouldn't ever come to reduce-phase")
    }

    override fun newInstance(left: EsNode, right: EsNode): BinaryOperator = EsAt(left, right)

    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)

    override fun flatten(): EsNode {
        val newClauses = (left as EffectSchema).clauses.filter {
            val outcome = it.right.getOutcome()
            outcome == null || outcome.followsFrom(right as Outcome)
        }
        return EffectSchema(newClauses)
    }
}