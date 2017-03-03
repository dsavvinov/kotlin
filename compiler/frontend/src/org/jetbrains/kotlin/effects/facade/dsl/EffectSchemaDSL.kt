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

package org.jetbrains.kotlin.effects.facade.dsl

import org.jetbrains.kotlin.effects.structure.general.EsFunction
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.operators.Imply

class EffectSchemaBuilder {
    val clauses: MutableList<Imply> = mutableListOf()

    infix fun (EsNode).to(other: EsNode): Unit {
        clauses += Imply(this, other)
    }

    fun build(): EffectSchema {
        return EffectSchema(clauses)
    }
}

fun EsFunction.defineSchema(description: (EffectSchemaBuilder).() -> Unit): EffectSchema {
    val effectSchema = EffectSchemaBuilder().apply { description() }.build()
    this.schema = effectSchema
    return effectSchema
}