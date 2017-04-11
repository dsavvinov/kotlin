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

import org.jetbrains.kotlin.effects.structure.effects.EsCalls
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.general.lift
import org.jetbrains.kotlin.effects.structure.schema.*
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.Imply
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator

/**
 * Reduces (if possible) given EffectSchema-tree.
 *
 * Currently it works as a simple constant-expression evaluator,
 * using operators-defined virtual `reduce()` call.
 */
class EffectSchemaReducer : SchemaVisitor<EsNode> {
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

    override fun visit(esReturns: EsReturns): EsNode = EsReturns(esReturns.value.accept(this))

    override fun visit(cons: Cons): EsNode {
        val evaluatedHead = cons.head.accept(this)
        val evaluatedTail = cons.tail.accept(this) as NodeSequence
        return Cons(evaluatedHead, evaluatedTail)
    }

    override fun visit(nil: Nil): EsNode = nil

    override fun visit(esCalls: EsCalls): EsNode = esCalls
}

fun EsNode.reduce() : EffectSchema?  {
    val result = EffectSchemaReducer().let { this.accept(it) } as? Term
    return result?.castToSchema()
}