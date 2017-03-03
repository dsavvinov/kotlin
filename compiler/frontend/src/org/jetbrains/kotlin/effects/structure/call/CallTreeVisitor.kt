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

package org.jetbrains.kotlin.effects.structure.call

import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsType
import org.jetbrains.kotlin.effects.structure.general.EsVariable

interface CallTreeVisitor<out T> {
    fun visit(node: CtNode): T =
            throw IllegalStateException("Error: top-level type reached but no overloads were found!")

    fun visit(call: CtCall): T = visit(call as CtNode)

    fun visit(variable: EsVariable): T = visit(variable as CtNode)

    fun visit(constant: EsConstant): T = visit(constant as CtNode)

    fun visit(type: EsType): T = visit(type as CtNode)
}
