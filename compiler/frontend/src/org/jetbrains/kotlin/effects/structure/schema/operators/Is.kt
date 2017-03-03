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

import org.jetbrains.kotlin.effects.structure.EsBoolean
import org.jetbrains.kotlin.effects.structure.effects.Returns
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsType
import org.jetbrains.kotlin.effects.structure.lift
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.Term
import org.jetbrains.kotlin.effects.visitors.helpers.transform

data class Is(override val arg: EsNode, val type: EsType) : UnaryOperator {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
    override fun newInstance(arg: EsNode): UnaryOperator = Is(arg, type)

    override fun reduce(): EsNode {
        if (arg is EsConstant) {
            return (arg.type == type).lift()
        }

        return this
    }

    override fun flatten(): EsNode {
        if (arg !is Term) return this

        val leftSchema: EffectSchema = arg.castToSchema()

        val combinedClauses = mutableListOf<Imply>()

        for ((lhs, rhs) in leftSchema.clauses) {
            val rewritedRhs = rhs.transform {
                if (it !is Returns) {
                    return@transform it
                }

                // Otherwise evaluate Is-operator and update Returns-clause accordingly
                return@transform Returns(Is(it.value, type), EsBoolean)
            }

            combinedClauses += Imply(lhs, rewritedRhs)
        }

        return EffectSchema(combinedClauses)
    }
}