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

package org.jetbrains.kotlin.effects.visitors.helpers

import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.effects.Outcome
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.Cons
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.Nil
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator


class Searcher(val predicate: (EsNode) -> Boolean) : SchemaVisitor<Unit> {
    val buffer: MutableList<EsNode> = mutableListOf()

    private fun tryAdd(node: EsNode) {
        if (predicate(node)) {
            buffer.add(node)
        }
    }

    override fun visit(node: EsNode) = tryAdd(node)

    override fun visit(schema: EffectSchema) {
        schema.clauses.forEach { it.accept(this) }
        tryAdd(schema)
    }

    override fun visit(variable: EsVariable) = tryAdd(variable)

    override fun visit(constant: EsConstant) = tryAdd(constant)

    override fun visit(esReturns: EsReturns) {
        esReturns.value.accept(this)
        tryAdd(esReturns)
    }

    override fun visit(binaryOperator: BinaryOperator) {
        binaryOperator.left.accept(this)
        binaryOperator.right.accept(this)
        tryAdd(binaryOperator)
    }

    override fun visit(unaryOperator: UnaryOperator) {
        unaryOperator.arg.accept(this)
        tryAdd(unaryOperator)
    }

    override fun visit(cons: Cons) {
        cons.head.accept(this)
        cons.tail.accept(this)
        tryAdd(cons)
    }

    override fun visit(nil: Nil) = tryAdd(nil)
}

fun (EsNode).findAll(predicate: (EsNode) -> Boolean): List<EsNode> =
        Searcher(predicate).let { accept(it); it.buffer }

fun (EsNode).firstOrNull(predicate: (EsNode) -> Boolean): EsNode? =
        findAll(predicate).firstOrNull()

fun (EsNode).getOutcome() : Outcome? = firstOrNull { it is EsReturns || it is EsThrows } as Outcome?

fun (EsNode).contains(predicate: (EsNode) -> Boolean) =
        findAll(predicate).isNotEmpty()