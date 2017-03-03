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

/**
 * Utility class that holds state of the effects-combining pipeline.

 * This is needed for combining Effects with complex rules,
 * such as Outcome, for example, when we have to finish pipeline
 * abruptly when encounter unsuccessful Outcome on the left-side.
 */
data class EffectsPipelineFlags(
        private var isVetoed: Boolean = false   /** After vetoed, return pipeline immediately, resulting in last result **/
) {
    fun veto() { isVetoed = true }
    fun isVetoed() = isVetoed
}