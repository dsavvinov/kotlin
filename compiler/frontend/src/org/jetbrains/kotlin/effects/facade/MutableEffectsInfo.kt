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

package org.jetbrains.kotlin.effects.facade

import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfoFactory
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.isSubtypeOf

class MutableEffectsInfo {
    private val typeInfo: MutableMap<DataFlowValue, EsTypeInfoHolder> = mutableMapOf()
    private val valueInfo: MutableMap<DataFlowValue, EsValueInfoHolder> = mutableMapOf()
    private val invocationsInfo: MutableMap<DataFlowValue, InvocationsInfo> = mutableMapOf()

    fun subtype(lhs: DataFlowValue, type: KotlinType): MutableEffectsInfo {
        val newHolder = InfoHolder.Valid(EsTypeInfo(lhs, mutableSetOf(type), mutableSetOf()))
        typeInfo.update(lhs, newHolder, { l, r -> l.andType(r) })
        return this
    }

    fun notSubtype(lhs: DataFlowValue, type: KotlinType): MutableEffectsInfo {
        val newHolder = InfoHolder.Valid(EsTypeInfo(lhs, mutableSetOf(), mutableSetOf(type)))
        typeInfo.update(lhs, newHolder, { l, r -> l.andType(r) })
        return this
    }

    fun equate(lhs: DataFlowValue, rhs: DataFlowValue): MutableEffectsInfo {
        val newHolder = InfoHolder.Valid(EsValueInfo(lhs, setOf(rhs), setOf()))
        valueInfo.update(lhs, newHolder, { l, r -> l.andValue(r) })
        return this
    }

    fun disequate(lhs: DataFlowValue, rhs: DataFlowValue): MutableEffectsInfo {
        val newHolder = InfoHolder.Valid(EsValueInfo(lhs, setOf(), setOf(rhs)))
        valueInfo.update(lhs, newHolder, { l, r -> l.andValue(r) })
        return this
    }

    fun calls(lhs: DataFlowValue, count: Int): MutableEffectsInfo {
        val newInfo = InvocationsInfo.fromInt(count)
        invocationsInfo.update(lhs, newInfo, { l, r -> l.and(r) })
        return this
    }

    fun or(other: MutableEffectsInfo): MutableEffectsInfo {
        other.typeInfo.forEach { typeInfo.update(it.key, it.value, { l, r -> l.orType(r) } ) }
        other.valueInfo.forEach { valueInfo.update(it.key, it.value, { l, r -> l.orValue(r) } ) }
        other.invocationsInfo.forEach { invocationsInfo.update(it.key, it.value, { l, r -> l.or(r) }) }
        return this
    }

    fun and(other: MutableEffectsInfo): MutableEffectsInfo {
        other.typeInfo.forEach { typeInfo.update(it.key, it.value, { l, r -> l.andType(r) } ) }
        other.valueInfo.forEach { valueInfo.update(it.key, it.value, { l, r -> l.andValue(r) } ) }
        other.invocationsInfo.forEach { invocationsInfo.update(it.key, it.value, { l, r -> l.and(r) }) }
        return this
    }

    fun getTypes(dfv: DataFlowValue): List<KotlinType> = (typeInfo[dfv] as? InfoHolder.Valid)?.value?.subtypes?.toList() ?: listOf()

    fun toDataFlowInfo(languageVersionSettings: LanguageVersionSettings): DataFlowInfo {
        val allValues = getAllValues()

        var resultingDataFlow = DataFlowInfoFactory.EMPTY

        for (dataFlowValue in allValues) {
            typeInfo[dataFlowValue]?.let {
                when (it) {
                    is InfoHolder.Valid ->
                        it.value.subtypes.forEach { resultingDataFlow = resultingDataFlow.establishSubtyping(dataFlowValue, it, languageVersionSettings) }
                    is InfoHolder.Impossible -> return DataFlowInfo.EMPTY
                }
            }

            valueInfo[dataFlowValue]?.let {
                when (it) {
                    is InfoHolder.Valid -> {
                        it.value.equals.forEach { resultingDataFlow = resultingDataFlow.equate(dataFlowValue, it, false, languageVersionSettings) }
                        it.value.notEquals.forEach { resultingDataFlow = resultingDataFlow.disequate(dataFlowValue, it, languageVersionSettings) }
                    }
                    is InfoHolder.Impossible -> return DataFlowInfo.EMPTY
                }
            }
        }

        return resultingDataFlow
    }

    fun getInvocationsInfo(dataFlowValue: DataFlowValue): InvocationsInfo? = invocationsInfo[dataFlowValue]

    private fun getAllValues(): Set<DataFlowValue> {
        val allValues = mutableSetOf<DataFlowValue>()

        allValues.addAll(typeInfo.keys)
        allValues.addAll(valueInfo.keys)
        allValues.addAll(invocationsInfo.keys)

        return allValues
    }

    private fun <K, V> MutableMap<K, V>.update(key: K, value: V, reducer: (V, V) -> V) {
        val newValue = this[key]?.let { reducer(it, value) } ?: value
        put(key, newValue)
    }

}