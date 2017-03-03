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

import org.jetbrains.kotlin.effects.structure.schema.operators.Is
import org.jetbrains.kotlin.effects.structure.schema.operators.Not
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsType
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.Equal
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator

/**
 * Collects statements about variables into given mutable maps
 */
data class Collector(
        val varsValues: MutableMap<EsVariable, EsConstant>,
        val varsTypes: MutableMap<EsVariable, EsType>) : SchemaVisitor<Unit>
{
    private var isInverted : Boolean = false

    fun inverted(body: () -> Unit) = {
        isInverted.xor(true)
        body()
        isInverted.xor(true)
    }

    override fun visit(node: EsNode) = Unit

    override fun visit(binaryOperator: BinaryOperator) {
        binaryOperator.left.accept(this)
        binaryOperator.right.accept(this)
    }

    override fun visit(unaryOperator: UnaryOperator) {
        unaryOperator.arg.accept(this)
    }

    override fun visit(isOperator: Is) {
        if (isOperator.arg is EsVariable) {
            varsTypes[isOperator.arg] = isOperator.type
        }
    }

    override fun visit(equalOperator: Equal) {
        if (equalOperator.left is EsVariable && equalOperator.right is EsConstant) {
            varsValues[equalOperator.left] = equalOperator.right
            varsTypes[equalOperator.left] = equalOperator.right.type
            return
        }

        equalOperator.left.accept(this)
        equalOperator.right.accept(this)
    }

    override fun visit(not: Not) {
        inverted { not.arg.accept(this) }
    }
}

fun (EsNode).collect(varsValues: MutableMap<EsVariable, EsConstant>,
                     varsTypes: MutableMap<EsVariable, EsType>) =
        Collector(varsValues, varsTypes).let { this.accept(it) }
