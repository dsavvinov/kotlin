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

package org.jetbrains.kotlin.effects.structure.schema

import org.jetbrains.kotlin.effects.structure.effects.EsCalls
import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.effects.Outcome
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.operators.*

interface SchemaVisitor<out T> {

    // Generic nodes
    fun visit(node: EsNode): T = throw IllegalStateException("Top-level of nodes hierarchy reached, no overloads found")
    fun visit(schema: EffectSchema): T = visit(schema as EsNode)
    fun visit(variable: EsVariable): T = visit(variable as EsNode)
    fun visit(constant: EsConstant): T = visit(constant as EsNode)

    fun visit(nodeSequence: NodeSequence): T = visit(nodeSequence as EsNode)
    fun visit(cons: Cons): T = visit(cons as NodeSequence)
    fun visit(nil: Nil): T = visit(nil as NodeSequence)

    // Operators
    fun visit(operator: Operator): T = visit(operator as EsNode)

    fun visit(imply: Imply): T = visit(imply as BinaryOperator)
    fun visit(binaryOperator: BinaryOperator): T = visit(binaryOperator as Operator)
    fun visit(unaryOperator: UnaryOperator): T = visit(unaryOperator as Operator)

    fun visit(esEqualOperator: EsEqual): T = visit(esEqualOperator as BinaryOperator)
    fun visit(esOr: EsOr): T = visit(esOr as BinaryOperator)
    fun visit(and: EsAnd): T = visit(and as BinaryOperator)
    fun visit(esNot: EsNot): T = visit(esNot as UnaryOperator)
    fun visit(esIsOperator: EsIs): T = visit(esIsOperator as UnaryOperator)

    // Effects
    fun visit(effect: Effect): T = visit(effect as EsNode)
    fun visit(esCalls: EsCalls): T = visit(esCalls as Effect)

    // Outcomes
    fun visit(outcome: Outcome): T = visit(outcome as EsNode)
    fun visit(esReturns: EsReturns): T = visit(esReturns as Outcome)
    fun visit(throws: EsThrows): T = visit(throws as Outcome)


}
