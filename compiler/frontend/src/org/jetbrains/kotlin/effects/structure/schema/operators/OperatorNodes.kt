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

import org.jetbrains.kotlin.effects.structure.effects.EffectsPipelineFlags
import org.jetbrains.kotlin.effects.structure.effects.Returns
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.Operator
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.Term
import org.jetbrains.kotlin.effects.visitors.helpers.transformReturn

interface UnaryOperator : Operator {
    val arg: EsNode

    // 'Prototype' pattern
    fun newInstance(arg: EsNode) : UnaryOperator

    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)

    override fun flatten(): EsNode {
        if (arg !is EffectSchema) {
            // Nothing to do, move on
            return this
        }

        val schema = arg as EffectSchema
        val newClauses = schema.clauses.map {
            it.transformReturn { Returns(newInstance(it.value), it.type) }
        }

        return EffectSchema(newClauses)
    }
}

interface BinaryOperator : Operator {
    val left: EsNode
    val right: EsNode

    // 'Prototype' pattern
    fun newInstance(left: EsNode, right: EsNode) : BinaryOperator

    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)

    override fun flatten(): EsNode {
        if (left !is Term || right !is Term) return this

        val leftSchema: EffectSchema = (left as Term).castToSchema()
        val rightSchema: EffectSchema = (right as Term).castToSchema()

        return leftSchema.flattenWith(rightSchema, operator = this)
    }
}

private fun (EffectSchema).flattenWith(rightSchema: EffectSchema, operator: BinaryOperator): EffectSchema {
    val newClauses = rightSchema.clauses.flatMap { rightClause ->
        this.clauses.map { leftClause ->
            val flags = EffectsPipelineFlags()
            val left = leftClause.left.and(rightClause.left)
            val right = leftClause.effectsAsList().flatMap {
                it.merge(leftClause.effectsAsList(), rightClause.effectsAsList(), flags, operator)
            }
            org.jetbrains.kotlin.effects.structure.schema.operators.Imply(left, right)
        }
    }
    return EffectSchema(newClauses)
}