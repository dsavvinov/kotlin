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
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfoFactory
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.isSubtypeOf

class MutableEffectsInfo {
    private val subtypes: MutableMap<DataFlowValue, MutableSet<KotlinType>> = mutableMapOf()
    private val notSubtypes: MutableMap<DataFlowValue, MutableSet<KotlinType>> = mutableMapOf()
    private val equals: MutableMap<DataFlowValue, MutableSet<DataFlowValue>> = mutableMapOf()
    private val notEquals: MutableMap<DataFlowValue, MutableSet<DataFlowValue>> = mutableMapOf()
    private val invokations: MutableMap<DataFlowValue, MutableSet<Int>> = mutableMapOf()

    enum class InvokationsInfo {
        UNKNOWN,
        NOT_INVOKED,
        EXACTLY_ONCE,
        AT_LEAST_ONCE;
    }

    fun subtype(lhs: DataFlowValue, type: KotlinType): Unit { subtypes.put(lhs, type) }

    fun notSubtype(lhs: DataFlowValue, type: KotlinType): Unit { notSubtypes.put(lhs, type) }

    fun equate(lhs: DataFlowValue, rhs: DataFlowValue): Unit { equals.put(lhs, rhs) }

    fun disequate(lhs: DataFlowValue, rhs: DataFlowValue): Unit { notEquals.put(lhs, rhs) }

    fun calls(lhs: DataFlowValue, count: Int): Unit { invokations.put(lhs, count) }

    fun toDataFlowInfo(languageVersionSettings: LanguageVersionSettings): DataFlowInfo {
        val allValues = getAllValues()

        var resultingDataFlow = DataFlowInfoFactory.EMPTY

        for (dataFlowValue in allValues) {
            val consistentSubtypes = getConsistentSubtypes(dataFlowValue)
            consistentSubtypes.forEach { resultingDataFlow = resultingDataFlow.establishSubtyping(dataFlowValue, it, languageVersionSettings) }

            val consistentEquals = getConsistentEqualValues(dataFlowValue)
            consistentEquals.forEach { resultingDataFlow = resultingDataFlow.equate(dataFlowValue, it, false, languageVersionSettings) }

            val consistentNotEqualValues = getConsistentNotEqualValues(dataFlowValue)
            consistentNotEqualValues.forEach { resultingDataFlow = resultingDataFlow.disequate(dataFlowValue, it, languageVersionSettings) }
        }

        return resultingDataFlow
    }

    fun getInvokationsInfo(dataFlowValue: DataFlowValue): InvokationsInfo? = invokations.keys.map { Pair(it, getConsistentInvokationInfo(it)) }.toMap()[dataFlowValue]


    private fun getAllValues(): Set<DataFlowValue> {
        val allValues = mutableSetOf<DataFlowValue>()

        allValues.addAll(subtypes.keys)
        allValues.addAll(notSubtypes.keys)
        allValues.addAll(equals.keys)
        allValues.addAll(notEquals.keys)

        return allValues
    }

    private fun getConsistentSubtypes(dataFlowValue: DataFlowValue): List<KotlinType> {
        val result = mutableListOf<KotlinType>()

        val subtypesSet = subtypes[dataFlowValue] ?: setOf<KotlinType>()
        val notSubtypesSet = notSubtypes[dataFlowValue] ?: setOf<KotlinType>()

        for (type in subtypesSet) {
            // If there is at least one `notType` such that `type : notType`, then `type` can't be the consistent subtype
            if (!notSubtypesSet.any(type::isSubtypeOf))  {
                result += type
            }
        }

        return result
    }

    private fun getConsistentEqualValues(dataFlowValue: DataFlowValue): List<DataFlowValue> {
        val recordedValues = equals[dataFlowValue] ?: setOf<DataFlowValue>()
        val recordedNotEquals = notEquals[dataFlowValue] ?: setOf<DataFlowValue>()

        return if (recordedValues.size > 1) listOf() else (recordedValues - recordedNotEquals).toList()
    }

    private fun getConsistentNotEqualValues(dataFlowValue: DataFlowValue): List<DataFlowValue> {
        val recordedValues = equals[dataFlowValue] ?: setOf<DataFlowValue>()
        val recordedNotEquals = notEquals[dataFlowValue] ?: setOf<DataFlowValue>()

        if (recordedValues.size > 1) return listOf()    // at least two equals - impossible

        if (recordedValues.size == 1 && recordedValues.minus(recordedNotEquals).isEmpty())
            return listOf()  // value is equal and not equal to the one and the same value - impossible

        return recordedNotEquals.minus(recordedValues).toList()
    }

    private fun getConsistentInvokationInfo(dataFlowValue: DataFlowValue): InvokationsInfo {
        val recordedInvokations = invokations[dataFlowValue] ?: return InvokationsInfo.UNKNOWN

        if (recordedInvokations.isEmpty()) return InvokationsInfo.UNKNOWN

        val lowerBound = recordedInvokations.min()!!
        val upperBound = recordedInvokations.max()!!

        // Check if it is NOT_INVOKED, or EXACTLY_ONCE
        if (lowerBound == upperBound) {
            when(lowerBound) {
                0 -> return InvokationsInfo.NOT_INVOKED
                1 -> return InvokationsInfo.EXACTLY_ONCE
            }
        }

        // Now just decide between AT_LEAST_ONCE and UNKNOWN
        if (lowerBound >= 1) return InvokationsInfo.AT_LEAST_ONCE

        return InvokationsInfo.UNKNOWN
    }

    private fun <D> MutableMap<DataFlowValue, MutableSet<D>>.put(key: DataFlowValue, value: D)
            = get(key)?.add(value) ?: put(key, mutableSetOf(value))

}