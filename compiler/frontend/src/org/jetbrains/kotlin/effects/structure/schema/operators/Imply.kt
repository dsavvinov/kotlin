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
import org.jetbrains.kotlin.effects.structure.effects.Returns
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.lift
import org.jetbrains.kotlin.effects.structure.schema.*
import org.jetbrains.kotlin.effects.visitors.helpers.getOutcome
import org.jetbrains.kotlin.effects.visitors.helpers.toList

/**
 * Equivalent of logic implication in context of Effect System.
 */
data class Imply(override val left: EsNode, override val right: EsNode) : BinaryOperator {
    val effects: List<Effect> by lazy { right.toList().filterIsInstance<Effect>() }
    val outcome: Outcome? by lazy { right.getOutcome() }

    constructor(left: EsNode, effects: List<Effect>, outcome: Outcome?)
            : this(left, (effects + if (outcome != null) listOf(outcome) else listOf()).reduceRight<EsNode, EsNode> { effect, acc -> acc.and(effect) } )

    override fun newInstance(left: EsNode, right: EsNode): BinaryOperator = Imply(left, right)

    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)

    override fun reduce(): EsNode = this

    // Flattening for `Imply` is special as we effectively treat `Return(false)` as unsuccessful outcome
    // (it doesn't executes rhs-side effects). Therefore, we have to override default flattening
    // inherited from `BinaryOperator`.
    override fun flatten(): EsNode {
        if (left !is Term || right !is Term) {
            return this
        }

        val leftSchema = left.castToSchema()
        val rightSchema = right.castToSchema()

        return leftSchema.flattenImply(rightSchema)
    }

    private fun (EffectSchema).flattenImply(right: EffectSchema): EffectSchema {
        val resultedClauses = mutableListOf<Imply>()
        for (leftClause in clauses) {
            val leftOutcome = leftClause.outcome
            val leftEffects = leftClause.effects

            if (leftOutcome !is Returns) {
                // Add non-successfull clause as is
                resultedClauses.add(leftClause)
                continue
            }

            if (leftOutcome.value == false.lift()) {
                // Add side-effects of false-resulting clause but do not add return
                resultedClauses.add(Imply(leftClause.left, leftClause.effects, null))
            }

            // Now, as we know that left part is finishing and returns something,
            // we can add additional condition which will be true iff leftClause returned true
            val cond = Equal(leftOutcome.value, true.lift())

            for (rightClause in right.clauses) {
                // Form the premise for combined clause (note the `cond`)
                //      leftClause.left && rightClause.left && cond
                val premise = leftClause.left.and(rightClause.left).and(cond)

                // Form the conclusion for combined clause:
                //      leftEffects && rightEffects && rightOutcome
                val conclusion = leftEffects + rightClause.effects
                resultedClauses.add(Imply(premise, conclusion, rightClause.outcome))
            }
        }
        val result = EffectSchema(resultedClauses)
        return result
    }
}