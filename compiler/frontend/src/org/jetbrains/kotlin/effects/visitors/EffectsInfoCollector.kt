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

package org.jetbrains.kotlin.effects.visitors

import org.jetbrains.kotlin.effects.facade.MutableEffectsInfo
import org.jetbrains.kotlin.effects.structure.effects.EsCalls
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.effects.Outcome
import org.jetbrains.kotlin.effects.structure.effects.EsHints
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.Cons
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.Nil
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.*
import org.jetbrains.kotlin.effects.visitors.helpers.getOutcome
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue

/**
 * Builds data flow info, contained in the given Effect Schema/
 */
class EffectsInfoCollector(val condition: Outcome) : SchemaVisitor<MutableEffectsInfo>
{
    private var isInverted : Boolean = false

    fun inverted(body: () -> MutableEffectsInfo): MutableEffectsInfo{
        isInverted = isInverted.xor(true)
        val result = body()
        isInverted = isInverted.xor(true)
        return result
    }

    override fun visit(schema: EffectSchema): MutableEffectsInfo {
        var resultingInfo = MutableEffectsInfo()

        for (clause in schema.clauses) {
            val clauseOutcome = clause.getOutcome()
            if (clauseOutcome == null || condition.followsFrom(clauseOutcome)) {
                val clauseInfo = visit(clause)
                resultingInfo = resultingInfo.or(clauseInfo)
            }
        }

        return resultingInfo
    }

    override fun visit(node: EsNode) = MutableEffectsInfo()

    override fun visit(esOr: EsOr): MutableEffectsInfo {
        val left = esOr.left.accept(this)
        val right = esOr.right.accept(this)

        return left.or(right)
    }

    override fun visit(esAnd: EsAnd): MutableEffectsInfo {
        val left = esAnd.left.accept(this)
        val right = esAnd.right.accept(this)

        return left.and(right)
    }

    override fun visit(esIsOperator: EsIs): MutableEffectsInfo {
        if (esIsOperator.arg is EsVariable) {
            if (isInverted) {
                return MutableEffectsInfo().notSubtype(esIsOperator.arg.value, esIsOperator.type)
            } else {
                return MutableEffectsInfo().subtype(esIsOperator.arg.value, esIsOperator.type)
            }
        }
        return MutableEffectsInfo()
    }

    override fun visit(esEqualOperator: EsEqual): MutableEffectsInfo {
        val leftDFV: DataFlowValue
        val rightDFV: DataFlowValue

        if (esEqualOperator.left is EsVariable && esEqualOperator.right is EsConstant) {
            leftDFV = esEqualOperator.left.value
            rightDFV = esEqualOperator.right.dataFlowValue ?: return MutableEffectsInfo()
        } else if (esEqualOperator.left is EsVariable && esEqualOperator.right is EsVariable) {
            leftDFV = esEqualOperator.left.value
            rightDFV = esEqualOperator.right.value
        } else {
            val left = esEqualOperator.left.accept(this)
            val right = esEqualOperator.right.accept(this)
            return left.and(right)
        }

        if (isInverted) {
            return MutableEffectsInfo().disequate(leftDFV, rightDFV)
        } else {
            return MutableEffectsInfo().equate(leftDFV, rightDFV)
        }
    }

    override fun visit(esNot: EsNot): MutableEffectsInfo {
        return inverted { esNot.arg.accept(this) }
    }

    override fun visit(cons: Cons): MutableEffectsInfo {
        val head = cons.head.accept(this)
        val tail = cons.tail.accept(this)
        return head.and(tail)
    }

    override fun visit(esCalls: EsCalls): MutableEffectsInfo {
        val effectsInfo = MutableEffectsInfo()
        esCalls.callCounts.forEach { esVariable, count -> effectsInfo.calls(esVariable.value, count) }
        return effectsInfo
    }

    override fun visit(esHints: EsHints) {
        esHints.types.forEach { variable, typesSet -> typesSet.forEach { effectsInfo.subtype(variable.value, it) } }
    }

    override fun visit(nil: Nil) = MutableEffectsInfo()

    override fun visit(imply: Imply): MutableEffectsInfo {
        val premise = imply.left.accept(this)
        val conclusion = imply.right.accept(this)
        return premise.and(conclusion)
    }


}

fun EffectSchema.collectEffectsInfoAt(outcome: Outcome): MutableEffectsInfo = EffectsInfoCollector(outcome).visit(this)
