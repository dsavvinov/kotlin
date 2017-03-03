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

import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator

class Linearizer : SchemaVisitor<List<EsNode>> {
    override fun visit(node: EsNode): List<EsNode> = listOf(node)

    override fun visit(schema: EffectSchema): List<EsNode>
            = schema.clauses.flatMap { it.accept(this) } + listOf(schema)

    override fun visit(binaryOperator: BinaryOperator): List<EsNode>
            = binaryOperator.left.accept(this) + binaryOperator.right.accept(this) + listOf(binaryOperator)

    override fun visit(unaryOperator: UnaryOperator): List<EsNode> =
            unaryOperator.arg.accept(this) + listOf(unaryOperator)
}

fun (EsNode?).toList() : List<EsNode> = Linearizer().let { this?.accept(it) } ?: listOf()