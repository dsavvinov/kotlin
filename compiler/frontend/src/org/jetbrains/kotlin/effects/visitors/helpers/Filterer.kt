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

package org.jetbrains.kotlin.effects.visitors.helpers

import org.jetbrains.kotlin.effects.structure.effects.Calls
import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.effects.Returns
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsType
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.lift
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.Imply
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator

class Filterer(val predicate: (EsNode) -> Boolean) : SchemaVisitor<EsNode?> {
    override fun visit(schema: EffectSchema): EsNode? {
        if (!predicate(schema)) return null

        return EffectSchema(
                schema.clauses.map { it.accept(this) }.filterIsInstance<Imply>()
        )
    }

    override fun visit(variable: EsVariable): EsNode? {
        if (!predicate(variable)) return null
        return variable
    }

    override fun visit(constant: EsConstant): EsNode? {
        if (!predicate(constant)) return null
        return constant
    }

    override fun visit(type: EsType): EsNode? {
        if (!predicate(type)) return null
        return type
    }

    override fun visit(binaryOperator: BinaryOperator): EsNode? {
        if (!predicate(binaryOperator)) return null

        val filteredLhs = binaryOperator.left.accept(this) ?: return null
        val filteredRhs = binaryOperator.right.accept(this) ?: return null

        return binaryOperator.newInstance(filteredLhs, filteredRhs)
    }

    override fun visit(unaryOperator: UnaryOperator): EsNode? {
        if (!predicate(unaryOperator)) return null

        val filteredArg = unaryOperator.arg.accept(this) ?: return null

        return unaryOperator.newInstance(filteredArg)
    }

    override fun visit(throws: EsThrows): EsNode? {
        if (!predicate(throws)) return null

        return throws
    }

    override fun visit(returns: Returns): EsNode? {
        if (!predicate(returns)) return null

//        val filteredArg = returns.value.accept(this) ?: return null

        return returns
    }

    override fun visit(calls: Calls): EsNode? {
        if (!predicate(calls)) return null
        return calls
    }
}

fun (EsNode).filter(predicate: (EsNode) -> Boolean): EsNode? = Filterer(predicate).let { accept(it) }

fun (Imply).removeReturns(): Imply = Imply(left, right.filter { it !is Returns } ?: true.lift())