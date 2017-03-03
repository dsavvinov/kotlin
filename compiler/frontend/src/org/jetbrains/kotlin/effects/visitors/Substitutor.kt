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

import org.jetbrains.kotlin.effects.structure.general.EsFunction
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator

/**
 * Visits EffectSchema-tree and substitutes every occurence
 * of a given set of variables with corresponding node.
 *
 * Generally, used when we want to bind call-arguments to formal arguments.
 */
class Substitutor(val substs: Map<EsVariable, EsNode>) : SchemaVisitor<EsNode> {
    override fun visit(node: EsNode): EsNode = node

    override fun visit(schema: EffectSchema): EsNode {
        val substitutedEffects = schema.clauses.map { it.accept(this) as org.jetbrains.kotlin.effects.structure.schema.operators.Imply }
        return EffectSchema(substitutedEffects)
    }

    override fun visit(binaryOperator: BinaryOperator): EsNode =
            binaryOperator.newInstance(binaryOperator.left.accept(this), binaryOperator.right.accept(this))

    override fun visit(unaryOperator: UnaryOperator): EsNode =
            unaryOperator.newInstance(unaryOperator.arg.accept(this))

    override fun visit(variable: EsVariable): EsNode = substs[variable] ?: variable
}

fun (EffectSchema).bind(function: EsFunction, args: List<EsNode>) : EffectSchema {
    val substs = function.formalArgs.zip(args).toMap()

    val substitutor = Substitutor(substs)
    return substitutor.visit(this) as EffectSchema
}