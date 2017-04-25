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

package org.jetbrains.kotlin.effects.structure.schema.operators

import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.effects.facade.Unknown
import org.jetbrains.kotlin.effects.structure.general.*
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.visitors.EffectsInfoCollector

class EsTypeOf(override val left: EsNode, override val right: EsNode) : BinaryOperator {
    override fun flatten(): EsNode {
        if (right is EsConstant) return EsSimpleType(right.type)
        if (right !is EsVariable) throw IllegalStateException("Expected right-arg of EsTypeOf to be EsConstant or EsVariable, got $this")

        val collector = EffectsInfoCollector(Unknown)
        val collectedTypes = left.accept(collector).getTypes(right.value)

        // TODO: proper intersection type
        return if (collectedTypes.size == 1) EsSimpleType(collectedTypes[0]) else EsSimpleType(DefaultBuiltIns.Instance.nullableAnyType)
    }

    override fun reduce(): EsNode {
        throw IllegalStateException("EsTypeOf-operator shouldn't get to reduce-phase")
    }

    override fun newInstance(left: EsNode, right: EsNode): BinaryOperator = EsTypeOf(left, right)

    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
}