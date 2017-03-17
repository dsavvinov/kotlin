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

import org.jetbrains.kotlin.effects.facade.Unknown
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator


data class EsReturns(val value: EsNode) : Outcome {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)

    override fun isSuccessfull(): Boolean = true

    override fun merge(other: Outcome?, binaryOperator: BinaryOperator): Outcome {
        other ?: return this

        if (other !is EsReturns) {
            return other
        }

        return EsReturns(binaryOperator.newInstance(value, other.value))
    }


    override fun followsFrom(other: Outcome): Boolean {
        if (other !is EsReturns) return false

        // Returns(Unknown) conforms to any kind of Returns
        if (value == Unknown) return true

        // Variable can take any value, so anything follows from such Returns
        if (other.value is EsVariable) return true

        // If both Returns have a constant as their arg, then just compare them
        if (other.value is EsConstant && this.value is EsConstant) {
            return value == other.value
        }

        // Default case - conservatively assume that nothing conforms
        return false
    }
}
