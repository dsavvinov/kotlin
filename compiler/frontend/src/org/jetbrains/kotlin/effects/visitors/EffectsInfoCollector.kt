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
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.isSubtypeOf

/**
 * Builds data flow info, contained in the given Effect Schema/
 */
class EffectsInfoCollector : SchemaVisitor<Unit>
{
    private val types: MutableMap<DataFlowValue, KotlinType> = mutableMapOf()
    private val equalValues: MutableMap<DataFlowValue, DataFlowValue > = mutableMapOf()
    private val notEqualValues: MutableMap<DataFlowValue, MutableList<DataFlowValue>> = mutableMapOf()
    private var isInverted : Boolean = false

    fun getTypesInfo() : Map<DataFlowValue, KotlinType> = types

    fun getEqualInfo() : Map<DataFlowValue, DataFlowValue> = equalValues

    fun getNotEqualInfo() : Map<DataFlowValue, List<DataFlowValue>> = notEqualValues

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
            val dataFlowValue = esIsOperator.arg.value
            val newType = esIsOperator.type
            val oldType = types[esIsOperator.arg.value]

            // We rewrite old type if there was no old type, or new type is more specific than old
            if (oldType == null || esIsOperator.type.isSubtypeOf(oldType)) {
                types[esIsOperator.arg.value] = esIsOperator.type
            }
        }
    }

    override fun visit(esEqualOperator: EsEqual) {
        val leftDFV: DataFlowValue
        val rightDFV: DataFlowValue

        if (esEqualOperator.left is EsVariable && esEqualOperator.right is EsConstant) {
            leftDFV = esEqualOperator.left.value
            rightDFV = esEqualOperator.right.dataFlowValue ?: return
        } else if (esEqualOperator.left is EsVariable && esEqualOperator.right is EsVariable) {
            leftDFV = esEqualOperator.left.value
            rightDFV = esEqualOperator.right.value
        } else {
            esEqualOperator.left.accept(this)
            esEqualOperator.right.accept(this)
            return
        }

        if (isInverted) {
            notEqualValues.compute(leftDFV, { leftDFV, list -> list?.also { it.add(rightDFV) } ?: mutableListOf(rightDFV) } )
        } else {
            equalValues[leftDFV] = rightDFV
        }
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

fun (EsNode).collectDataFlowInfo(initialDataFlow: DataFlowInfo, languageVersionSettings: LanguageVersionSettings) : DataFlowInfo {
    val collector = EffectsInfoCollector()
    this.accept(collector)

    val types = collector.getTypesInfo()
    val equalInfo = collector.getEqualInfo()
    val notEqualInfo = collector.getNotEqualInfo()

    val allValues = mutableSetOf<DataFlowValue>()
    allValues.addAll(types.keys)
    allValues.addAll(equalInfo.keys)
    allValues.addAll(notEqualInfo.keys)

    var resultingDataFlow = initialDataFlow
    for (dataFlowValue in allValues) {
        types[dataFlowValue]?.let { resultingDataFlow = resultingDataFlow.establishSubtyping(dataFlowValue, it, languageVersionSettings) }

        equalInfo[dataFlowValue]?.let { resultingDataFlow = resultingDataFlow.equate(dataFlowValue, it, false, languageVersionSettings) }

        notEqualInfo[dataFlowValue]?.let { listOfNotEqualValues ->
            listOfNotEqualValues.forEach {
                resultingDataFlow = resultingDataFlow.disequate(dataFlowValue, it, languageVersionSettings) }
        }
    }

    return resultingDataFlow
}
