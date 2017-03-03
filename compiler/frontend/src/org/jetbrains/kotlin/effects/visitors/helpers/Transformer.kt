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

import org.jetbrains.kotlin.effects.structure.effects.Returns
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator

class Transformer(val transform: (EsNode) -> EsNode) : SchemaVisitor<EsNode> {
    override fun visit(node: EsNode): EsNode = transform(node)

    override fun visit(schema: EffectSchema): EsNode =
            EffectSchema(schema.clauses.map { it.accept(this) as org.jetbrains.kotlin.effects.structure.schema.operators.Imply })
                    .let(transform)

    override fun visit(binaryOperator: BinaryOperator): EsNode =
            binaryOperator
                    .newInstance(binaryOperator.left.accept(this), binaryOperator.right.accept(this))
                    .let(transform)

    override fun visit(unaryOperator: UnaryOperator): EsNode =
            unaryOperator
                    .newInstance(unaryOperator.arg.accept(this))
                    .let(transform)

    override fun visit(returns: Returns): EsNode = Returns(returns.value.accept(this), returns.type).let(transform)
}

fun (EsNode).transform(transform: (EsNode) -> EsNode) = Transformer(transform).let { accept(it) }

fun (org.jetbrains.kotlin.effects.structure.schema.operators.Imply).transformReturn(transform: (Returns) -> EsNode) : org.jetbrains.kotlin.effects.structure.schema.operators.Imply =
        transform { if (it is Returns) transform(it) else it } as org.jetbrains.kotlin.effects.structure.schema.operators.Imply