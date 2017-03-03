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

import org.jetbrains.kotlin.effects.structure.effects.Returns
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.lift
import org.jetbrains.kotlin.effects.structure.schema.Effect
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.visitors.helpers.filter
import org.jetbrains.kotlin.effects.visitors.helpers.getOutcome
import org.jetbrains.kotlin.effects.visitors.helpers.removeReturns
import org.jetbrains.kotlin.effects.visitors.helpers.toList

/**
 * Equivalent of logic implication in context of Effect System.
 */
data class Imply(override val left: EsNode, override val right: EsNode) : BinaryOperator {
    constructor(premise: EsNode, effects: List<Effect>) : this(
            premise,
            effects.reduceRight<EsNode, Effect> { effect, acc -> acc.and(effect) }
    )

    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)

    override fun newInstance(left: EsNode, right: EsNode): BinaryOperator = Imply(left, right)

    override fun flatten(): EsNode {
        return when(left) {
            is EffectSchema -> left.flattenImply(right)
            is EsVariable -> left.castToSchema().flattenImply(right)
            is EsConstant -> left.castToSchema().flattenImply(right)
            else -> throw IllegalStateException("Unknown left-type for Imply-operator: $left")
        }
    }

    override fun reduce(): EsNode = this




    private fun (EffectSchema).flattenImply(right: EsNode): EffectSchema {
        return when (right) {
            is EffectSchema -> flattenImply(right)
            else -> flattenImply(EffectSchema(listOf(Imply(true.lift(), right))))
        }
    }

    private fun (EffectSchema).flattenImply(right: EffectSchema): EffectSchema {
        val resultedClauses = mutableListOf<Imply>()
        for (leftClause in clauses) {
            val outcome = leftClause.getOutcome()

            if (outcome !is Returns) {
                // Add non-successfull clause as is
                resultedClauses.add(leftClause)
                continue
            }

            val lClauseWithoutReturns = leftClause.removeReturns()

            // Now, as we know that left part is finishing and returns something,
            // we can add additional condition which will be true iff leftClause returned true
            val cond = Equal(outcome.value, true.lift())

            for (rightClause in right.clauses) {
                // Form the premise for combined clause: leftClause.left && rightClause.left && cond
                val premise = leftClause.left.and(rightClause.left).and(cond)

                // Form the conclusion for combined clause:
                //      leftClause.right.removeOutcome() && rightClause.right
                val conclusion = lClauseWithoutReturns.right.and(rightClause.right)
                resultedClauses.add(Imply(premise, conclusion))
            }
        }
        val result = EffectSchema(resultedClauses)
        return result
    }
}

fun (Imply).effectsAsList() : List<Effect> = right.filter { it is Effect }.toList() as List<Effect>