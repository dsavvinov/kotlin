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

enum class InvocationsInfo(val representativeCount: Int) {
    UNKNOWN(-1),
    NOT_INVOKED(0),
    EXACTLY_ONCE(1),
    AT_LEAST_ONCE(2);

    companion object {
        fun fromInt(count: Int): InvocationsInfo = when {
            count == 0 -> NOT_INVOKED
            count == 1 -> EXACTLY_ONCE
            count > 1  -> AT_LEAST_ONCE
            else -> UNKNOWN
        }
    }

    fun and(other: InvocationsInfo): InvocationsInfo {
        // `and` is essentially summing counters together.
        // Note that UNKNOWN.and(<anything>) = UNKNOWN, which is ensured by the convention that negative counts correspond to UNKNOWN

        return fromInt(representativeCount + other.representativeCount)
    }

    fun or(other: InvocationsInfo): InvocationsInfo {
        // A.or(B) can be thought as segment [A.count; B.count], in which lie all possible invocation-counters
        val left = Math.min(representativeCount, other.representativeCount)
        val right = Math.max(representativeCount, other.representativeCount)

        // NOT_INVOKED or EXACTLY_ONCE can be result of this and only this case:
        if (left == right) return fromInt(left)

        // Now, decide between UNKNOWN and AT_LEAST_ONCE
        if (left >= 1) return AT_LEAST_ONCE

        return UNKNOWN
    }
}