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

import org.jetbrains.kotlin.effects.structure.schema.Effect
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator

data class EsThrows(val exception: Any?) : Outcome {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)

    override fun merge(left: List<Effect>, right: List<Effect>, flags: EffectsPipelineFlags, operator: BinaryOperator): List<Effect> {
        // Shutdown pipeline, and override result with left-effects.
        flags.veto()
        return left
    }

    override fun followsFrom(other: Outcome): Boolean {
        if (other !is EsThrows) return false

        // TODO: insert proper subtyping here
        return exception == other.exception
    }
}