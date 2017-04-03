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
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.effects.parsing.EsSignatureBuilder
import org.jetbrains.kotlin.effects.parsing.antlr.EffectSystemLexer
import org.jetbrains.kotlin.effects.parsing.antlr.EffectSystemParser
import org.jetbrains.kotlin.effects.parsing.antlr.ErrorReporter
import org.jetbrains.kotlin.effects.structure.general.EsFunction
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.lazy.descriptors.LazyAnnotationDescriptor

object EffectSchemasResolver {
    fun getEffectSchema(descriptor: CallableDescriptor, esResolutionContext: EsResolutionContext): EffectSchema? {
        val effectsAnnotationString = descriptor.getEffectsAnnotation()?.getEffectsString() ?: return null
        return effectsAnnotationString.parseES(descriptor, esResolutionContext) ?: return null
    }

    private fun CallableDescriptor.getEffectsAnnotation(): AnnotationDescriptor? = annotations.findAnnotation(FqName("kotlin.Effects"))

    private fun AnnotationDescriptor.getEffectsString(): String? = this.allValueArguments.toList()[0].second.value as String?

    private fun String.parseES(descriptor: CallableDescriptor, esResolutionContext: EsResolutionContext): EffectSchema? {
        try {
            val input = ANTLRInputStream(this)

            val lexer = EffectSystemLexer(input)
            lexer.removeErrorListeners()
            lexer.addErrorListener(ErrorReporter)

            val tokens = CommonTokenStream(lexer)

            val parser = EffectSystemParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(ErrorReporter)

            // EffectSchema should be the one and the only top-level node
            val effectSchemaCtx = parser.effectSchema()

            return EsSignatureBuilder(descriptor, esResolutionContext).visitEffectSchema(effectSchemaCtx)
        } catch (e: Exception) {
            esResolutionContext.trace ?: return null
            reportErrorWhileParsing(descriptor, esResolutionContext.trace, e.message ?: e.toString())
            return null
        }
    }

    private fun reportErrorWhileParsing(descriptor: CallableDescriptor, bindingTrace: BindingTrace, message: String) {
        val annotationEntry = (descriptor.getEffectsAnnotation() as? LazyAnnotationDescriptor)?.annotationEntry ?: return
        bindingTrace.report(Errors.EFFECTS_PARSING_ERROR.on(annotationEntry, message))
    }
}