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

import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.lift
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.NodeSequence
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.Term
import org.jetbrains.kotlin.effects.visitors.helpers.toNodeSequence
import org.jetbrains.kotlin.effects.visitors.helpers.transform
import org.jetbrains.kotlin.types.KotlinType

data class EsIs(override val arg: EsNode, val type: KotlinType) : UnaryOperator {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
    override fun newInstance(arg: EsNode): UnaryOperator = EsIs(arg, type)

    override fun reduce(): EsNode {
        if (arg is EsConstant) {
            return (arg.type == type).lift()
        }

        return this
    }

    override fun flatten(): EsNode {
        if (arg !is Term) return this
        if (arg !is EffectSchema) return this

        val leftSchema: EffectSchema = arg.castToSchema()

        val combinedClauses = mutableListOf<Imply>()

        for (clause in leftSchema.clauses) {
//            val rewritedRhs = rhs.transform {
//                if (it !is EsReturns) {
//                    return@transform it
//                }
//
//                // Otherwise evaluate EsIs-operator and update Returns-clause accordingly
//                return@transform EsReturns(EsIs(it.value, type))
//            } as NodeSequence
//
//            combinedClauses += Imply(lhs, rewritedRhs)

            val outcome = clause.outcome

            if (outcome == null || outcome !is EsReturns) {
                combinedClauses.add(clause)
                continue
            }

            val trueLhs = clause.left.and(EsIs(outcome.value, type))
            val trueRhs = clause.right.transform { if (it !is EsReturns) return@transform it else return@transform EsReturns(true.lift()) } as NodeSequence

            val falseLhs = clause.left.and(EsNot(EsIs(outcome.value, type)))
            val falseRhs = clause.right.transform { if (it !is EsReturns) return@transform it else return@transform EsReturns(false.lift()) } as NodeSequence

            combinedClauses.add(Imply(trueLhs, trueRhs))
            combinedClauses.add(Imply(falseLhs, falseRhs))
        }

        return EffectSchema(combinedClauses)
    }

    override fun toString(): String = "Is($arg, $type)"
}