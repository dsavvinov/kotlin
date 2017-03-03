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

package org.jetbrains.kotlin.effects.structure.effects

import org.jetbrains.kotlin.effects.structure.general.EsFunction
import org.jetbrains.kotlin.effects.structure.schema.Effect
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor

data class Calls(val callCounts: MutableMap<EsFunction, Int>) : Effect {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)

    override fun merge(other: Effect): Calls {
        val resultCalls = mutableMapOf<EsFunction, Int>()
        resultCalls.putAll(callCounts)
        for ((function, calls) in (other as Calls).callCounts) {
            resultCalls.merge(function, calls, Int::plus)
        }

        return Calls(resultCalls)
    }
}