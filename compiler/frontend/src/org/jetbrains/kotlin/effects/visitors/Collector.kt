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

import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.Cons
import org.jetbrains.kotlin.effects.structure.schema.Nil
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.*
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo
import org.jetbrains.kotlin.types.KotlinType

/**
 * Collects statements about variables into given mutable maps
 */
data class Collector(var dataFlowInfo: DataFlowInfo, val languageVersionSettings: LanguageVersionSettings) : SchemaVisitor<Unit>
{
    private var isInverted : Boolean = false

    fun inverted(body: () -> Unit) {
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

    override fun visit(esIsOperator: EsIs) {
        if (esIsOperator.arg is EsVariable) {
            dataFlowInfo = dataFlowInfo.establishSubtyping(esIsOperator.arg.value, esIsOperator.type, languageVersionSettings)
        }
    }

    override fun visit(esEqualOperator: EsEqual) {
        if (esEqualOperator.left is EsVariable && esEqualOperator.right is EsConstant) {
            TODO()
        }

        if (esEqualOperator.left is EsVariable && esEqualOperator.right is EsVariable) {
            if (isInverted) {
                dataFlowInfo = dataFlowInfo.disequate(esEqualOperator.left.value, esEqualOperator.right.value, languageVersionSettings)
            } else {
                dataFlowInfo = dataFlowInfo.equate(esEqualOperator.left.value, esEqualOperator.right.value, /* sameTypes = */ false, languageVersionSettings)
            }
        }

        esEqualOperator.left.accept(this)
        esEqualOperator.right.accept(this)
    }

    override fun visit(esNot: EsNot) {
        inverted { esNot.arg.accept(this) }
    }

    override fun visit(cons: Cons) {
        cons.head.accept(this)
        cons.tail.accept(this)
    }

    override fun visit(nil: Nil) = Unit

}

fun (EsNode).collect(dataFlowInfo: DataFlowInfo, languageVersionSettings: LanguageVersionSettings) : DataFlowInfo =
        Collector(dataFlowInfo, languageVersionSettings).also { this.accept(it) }.dataFlowInfo
