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

import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.DescriptorUtils
import org.jetbrains.kotlin.resolve.TypeResolver
import org.jetbrains.kotlin.resolve.calls.context.BasicCallResolutionContext
import org.jetbrains.kotlin.resolve.calls.context.CallResolutionContext
import org.jetbrains.kotlin.resolve.calls.context.ResolutionContext
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext

class EsResolutionContext(
        val context: BindingContext,
        val psiFactory: KtPsiFactory,
        val typeResolver: TypeResolver,
        val lexicalScope: LexicalScope,
        val moduleDescriptor: ModuleDescriptor,
        val trace: BindingTrace? = null
) {
    companion object {
        fun create(resolutionContext: ResolutionContext<*>, ktPsiFactory: KtPsiFactory, typeResolver: TypeResolver): EsResolutionContext =
            EsResolutionContext(
                    resolutionContext.trace.bindingContext,
                    ktPsiFactory,
                    typeResolver,
                    resolutionContext.scope,
                    DescriptorUtils.getContainingModule(resolutionContext.scope.ownerDescriptor),
                    resolutionContext.trace
            )

        fun create(expressionTypingContext: ExpressionTypingContext, ktPsiFactory: KtPsiFactory, typeResolver: TypeResolver): EsResolutionContext
                = EsResolutionContext(
                expressionTypingContext.trace.bindingContext,
                ktPsiFactory,
                typeResolver,
                expressionTypingContext.scope,
                DescriptorUtils.getContainingModule(expressionTypingContext.scope.ownerDescriptor),
                expressionTypingContext.trace
        )
    }
}