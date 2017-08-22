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

package org.jetbrains.kotlin.effectsystem.resolving.dsl

import org.jetbrains.kotlin.effectsystem.structure.ESFunctor
import org.jetbrains.kotlin.psi.KtNamedFunction

class FunctorProvider(private val function: KtNamedFunction, private val computation: () -> Unit) {
    var functor: ESFunctor? = null
        get() = if (isProcessed) field else kotlin.run { computation(); field }
        set(value) {
            assert(!isProcessed) { "Re-initialization of functor provider for function ${function.name}" }
            isProcessed = true
            field = value
        }
    private var isProcessed: Boolean = false

    companion object {
        fun noFunctor(function: KtNamedFunction) = FunctorProvider(function, {})
    }
}