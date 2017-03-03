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

/**
 * Tries to advance evaluation in a given EffectSchema-tree.
 */
class Evaluator : SchemaVisitor<EsNode> {
    override fun visit(schema: EffectSchema): EsNode {
        val effects = schema.clauses.map { it.accept(this) }
        val filteredEffects = effects.filterIsInstance<Imply>().filter { it.left != false.lift() }
        return EffectSchema(filteredEffects)
    }

    override fun visit(variable: EsVariable): EsNode = variable

    override fun visit(constant: EsConstant): EsNode = constant

    override fun visit(binaryOperator: BinaryOperator): EsNode {
        val evaluatedLhs = binaryOperator.left.accept(this)
        val evaluatedRhs = binaryOperator.right.accept(this)

        return binaryOperator.newInstance(evaluatedLhs, evaluatedRhs).reduce()
    }

    override fun visit(unaryOperator: UnaryOperator): EsNode {
        val evaluatedArg = unaryOperator.arg.accept(this)

        return unaryOperator.newInstance(evaluatedArg).reduce()
    }

    override fun visit(throws: EsThrows): EsNode = throws

    override fun visit(returns: Returns): EsNode = returns
}

fun (EsNode).evaluate() : EsNode = Evaluator().let { this.accept(it) }