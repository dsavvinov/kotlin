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
import org.jetbrains.kotlin.effects.structure.schema.*
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator

class Linearizer : SchemaVisitor<Unit> {
    private val buffer: MutableList<EsNode> = mutableListOf()

    override fun visit(cons: Cons) {
        if (cons.head is NodeSequence) cons.head.accept(this) else buffer += cons.head
        cons.tail.accept(this)
    }

    override fun visit(nil: Nil) {}

    fun toList(): List<EsNode> = buffer
}

fun (NodeSequence).toList(): List<EsNode> = Linearizer().also { this.accept(it) }.toList()
fun (List<EsNode>).toNodeSequence(): NodeSequence = foldRight(Nil, ::Cons)
fun (EsNode).toNodeSequence(): NodeSequence = Cons(this, Nil)