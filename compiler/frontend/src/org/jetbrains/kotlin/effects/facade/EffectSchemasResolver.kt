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

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.effects.parsing.EsSignatureBuilder
import org.jetbrains.kotlin.effects.parsing.antlr.EffectSystemLexer
import org.jetbrains.kotlin.effects.parsing.antlr.EffectSystemParser
import org.jetbrains.kotlin.effects.structure.general.EsFunction
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.name.FqName

object EffectSchemasResolver {
    fun getEffectSchema(descriptor: CallableDescriptor, esResolutionUtils: EsResolutionUtils): EffectSchema? {
        val effectsAnnotationString = descriptor.getEffectsAnnotation() ?: return null
        return effectsAnnotationString.parseES(descriptor, esResolutionUtils) ?: return null
    }

    private fun CallableDescriptor.getEffectsAnnotation(): String? {
        val annotation = annotations.findAnnotation(FqName("kotlin.Effects"))
        return annotation?.allValueArguments?.toList()?.let { it[0].second.value as String? }
    }

    private fun String.parseES(descriptor: CallableDescriptor, esResolutionUtils: EsResolutionUtils): EffectSchema? {
        val input = ANTLRInputStream(this)

        val tokens = CommonTokenStream(EffectSystemLexer(input))

        // EffectSchema should be the one and the only top-level node
        val effectSchemaCtx = EffectSystemParser(tokens).effectSchema()

        return EsSignatureBuilder(descriptor, esResolutionUtils).visitEffectSchema(effectSchemaCtx)
    }
}