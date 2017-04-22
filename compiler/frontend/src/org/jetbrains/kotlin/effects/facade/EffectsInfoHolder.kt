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

import org.jetbrains.kotlin.effects.facade.InfoHolder.*
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.types.KotlinType

sealed class InfoHolder<out D> {
    class Valid<out D>(val value: D) : InfoHolder<D>()

    class Unknown<out D> : InfoHolder<D>()

    class Impossible<out D> : InfoHolder<D>()

    fun <U, R> bind2(other: InfoHolder<U>, combiner: (D, U) -> InfoHolder<R>): InfoHolder<R> {
        if (this is Impossible || other is Impossible) return Impossible()

        if (this is Unknown || other is Unknown) return Unknown()

        return combiner((this as Valid).value, (other as Valid).value)
    }
}

class EsTypeInfo(val value: DataFlowValue, val subtypes: MutableSet<KotlinType>, val notSubtypes: MutableSet<KotlinType>)

typealias EsTypeInfoHolder = InfoHolder<EsTypeInfo>

fun EsTypeInfoHolder.andType(other: EsTypeInfoHolder): EsTypeInfoHolder =
        this.bind2(other) { thisInfo: EsTypeInfo, otherInfo: EsTypeInfo ->
            val allSubtypes = thisInfo.subtypes + otherInfo.subtypes
            val notSubtypes = thisInfo.notSubtypes + otherInfo.notSubtypes

            if (notSubtypes.intersect(allSubtypes).isNotEmpty()) {
                return@bind2 Impossible<EsTypeInfo>()
            }

            return@bind2 Valid(EsTypeInfo(thisInfo.value, allSubtypes.toMutableSet(), notSubtypes.toMutableSet()))
        }

fun EsTypeInfoHolder.orType(other: EsTypeInfoHolder): EsTypeInfoHolder =
        this.bind2(other) { thisInfo: EsTypeInfo, otherInfo: EsTypeInfo ->
            // Treat union-types as a UNKNOWN
            if (thisInfo.subtypes.isNotEmpty() && otherInfo.subtypes.isNotEmpty()) return@bind2 Unknown<EsTypeInfo>()
            if (thisInfo.notSubtypes.isNotEmpty() && otherInfo.subtypes.isNotEmpty()) return@bind2 Unknown<EsTypeInfo>()

            // Corner case, when at least one of arguments is empty set (thus not giving a rise to union-type): use `and`
            return@bind2 this.andType(other)
        }


class EsValueInfo(val dfv: DataFlowValue, val equals: Set<DataFlowValue>, val notEquals: Set<DataFlowValue>)

typealias EsValueInfoHolder = InfoHolder<EsValueInfo>

fun EsValueInfoHolder.andValue(other: EsValueInfoHolder): EsValueInfoHolder {
    return this.bind2(other) { thisInfo: EsValueInfo, otherInfo: EsValueInfo ->
        val allEquals = thisInfo.equals + otherInfo.equals
        val notEquals = thisInfo.notEquals + otherInfo.notEquals

        // Note that unlike types, dataFlowValue can't have more than one value
        if (allEquals.size > 1) return@bind2 Impossible<EsValueInfo>()

        if (allEquals.intersect(notEquals).isNotEmpty()) {
            return@bind2 Impossible<EsValueInfo>()
        }

        return@bind2 Valid(EsValueInfo(thisInfo.dfv, allEquals, notEquals))
    }
}

fun EsValueInfoHolder.orValue(other: EsValueInfoHolder): EsValueInfoHolder {
    return this.bind2(other) { thisInfo: EsValueInfo, otherInfo: EsValueInfo ->
        if (thisInfo.equals.isNotEmpty() && otherInfo.equals.isNotEmpty()) return@bind2 Unknown<EsValueInfo>()
        if (thisInfo.notEquals.isNotEmpty() && otherInfo.notEquals.isNotEmpty()) return@bind2 Unknown<EsValueInfo>()

        return@bind2 this.andValue(other)
    }
}