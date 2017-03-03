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

import org.jetbrains.kotlin.effects.structure.call.CallTreeVisitor
import org.jetbrains.kotlin.effects.structure.call.CtCall
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsType
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.intrinsicFunctions
import org.jetbrains.kotlin.effects.structure.kludgeFunctions
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema

/**
 * Generates tree of effect schemas given a call-tree
 */
class EffectSchemaGenerator : CallTreeVisitor<EsNode> {
    override fun visit(call: CtCall): EsNode {
        val substitutedArgs = call.childs.map { it.accept(this) }
        val basicSchema = call.function.schema
                          ?: kludgeFunctions[call.function]?.invoke(substitutedArgs) as? EffectSchema
                          ?: return intrinsicFunctions[call.function]?.invoke(substitutedArgs)
                          ?: throw IllegalArgumentException("Effect schema for function ${call.function} is not defined")

        return basicSchema.bind(call.function, substitutedArgs)
    }

    override fun visit(variable: EsVariable): EsNode = variable

    override fun visit(constant: EsConstant): EsNode = constant

    override fun visit(type: EsType): EsNode = type
}

fun (CtCall).generateEffectSchema() : EffectSchema {
    val generator = EffectSchemaGenerator()

    return accept(generator) as EffectSchema
}