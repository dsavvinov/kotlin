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

import org.jetbrains.kotlin.effects.structure.general.EsFunction
import org.jetbrains.kotlin.effects.structure.general.EsType

/**
 * Represents Call-Tree structure.
 *
 * Call-Tree is built from AST that represents particular call.
 */
interface CtNode {
    fun <T> accept(visitor: CallTreeVisitor<T>): T = visitor.visit(this)
}

data class CtCall(val function: EsFunction, val childs: List<CtNode>) : CtNode {
    override fun <T> accept(visitor: CallTreeVisitor<T>): T = visitor.visit(this)
}

data class CtIs(val left: CtNode, val type: EsType) : CtNode {
    override fun <T> accept(visitor: CallTreeVisitor<T>): T = visitor.visit(this)
}

data class CtEqual(val left: CtNode, val right: CtNode) : CtNode {
    override fun <T> accept(visitor: CallTreeVisitor<T>): T = visitor.visit(this)
}

data class CtNot(val arg: CtNode) : CtNode {
    override fun <T> accept(visitor: CallTreeVisitor<T>): T = visitor.visit(this)
}