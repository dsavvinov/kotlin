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

import org.jetbrains.kotlin.effects.structure.effects.Calls
import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.effects.Outcome
import org.jetbrains.kotlin.effects.structure.effects.Returns
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator

/**
 * Tries to flatten a given EffectSchema-tree, that is,
 * returned such value so that it will not contain any
 * nested effect schemas.
 */
class Combiner : SchemaVisitor<EsNode> {
    override fun visit(schema: EffectSchema): EsNode {
        val evaluatedEffects = schema.clauses.flatMap {
            val res = it.accept(this)
            when (res) {
                is org.jetbrains.kotlin.effects.structure.schema.operators.Imply -> listOf(res)
                is EffectSchema -> res.clauses
                else -> throw IllegalStateException()
            }
        }

        return EffectSchema(evaluatedEffects)
    }

    override fun visit(variable: EsVariable): EsNode = variable

    override fun visit(constant: EsConstant): EsNode = constant

    override fun visit(binaryOperator: BinaryOperator): EsNode {
        val lhs = binaryOperator.left.accept(this)
        val rhs = binaryOperator.right.accept(this)

        return binaryOperator.newInstance(lhs, rhs).flatten()
    }

    override fun visit(unaryOperator: UnaryOperator): EsNode {
        val arg = unaryOperator.arg.accept(this)

        return unaryOperator.newInstance(arg).flatten()
    }

    override fun visit(throws: EsThrows) = throws

    override fun visit(returns: Returns): EsNode {
        val arg = returns.value.accept(this)
        return Returns(arg)
    }

    override fun visit(calls: Calls): EsNode = calls


}

fun (EsNode).flatten() : EsNode = Combiner().let { accept(it) }