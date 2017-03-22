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
import org.jetbrains.kotlin.resolve.TypeResolver
import org.jetbrains.kotlin.resolve.calls.context.CallResolutionContext

class EsResolutionUtils(
        val context: BindingContext,
        val psiFactory: KtPsiFactory,
        val typeResolver: TypeResolver? = null,
        val moduleDescriptor: ModuleDescriptor
) {
    lateinit var trace: BindingTrace
    lateinit var resolutionContext: CallResolutionContext<*>

    constructor(resolutionContext: CallResolutionContext<*>, psiFactory: KtPsiFactory, typeResolver: TypeResolver?, moduleDescriptor: ModuleDescriptor)
            : this(resolutionContext.trace.bindingContext,
                   psiFactory,
                   typeResolver,
                   moduleDescriptor) {
        trace = resolutionContext.trace
        this.resolutionContext = resolutionContext
    }
}