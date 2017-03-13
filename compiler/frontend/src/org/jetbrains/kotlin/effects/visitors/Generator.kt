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

import org.jetbrains.kotlin.effects.facade.EffectSchemasResolver
import org.jetbrains.kotlin.effects.structure.call.*
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.operators.EsEqual
import org.jetbrains.kotlin.effects.structure.schema.operators.EsIs
import org.jetbrains.kotlin.effects.structure.schema.operators.EsNot

/**
 * Generates tree of effect schemas given a call-tree
 */
class EffectSchemaGenerator : CallTreeVisitor<EsNode> {
    override fun visit(call: CtCall): EsNode {
        val substitutedArgs = call.childs.map { it.accept(this) }
        val basicSchema = EffectSchemasResolver.getEffectSchema(call.function)

        return basicSchema!!.bind(call.function, substitutedArgs)
    }

    override fun visit(ctIs: CtIs): EsNode = EsIs(ctIs.left.accept(this), ctIs.type)

    override fun visit(ctEqual: CtEqual): EsNode = EsEqual(ctEqual.left.accept(this), ctEqual.right.accept(this))

    override fun visit(ctNot: CtNot): EsNode = EsNot(ctNot.arg.accept(this))

    override fun visit(variable: EsVariable): EsNode = variable

    override fun visit(constant: EsConstant): EsNode = constant
}

fun (CtCall).generateEffectSchema() : EffectSchema {
    val generator = EffectSchemaGenerator()

    return accept(generator) as EffectSchema
}