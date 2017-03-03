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

import org.jetbrains.kotlin.effects.structure.effects.Calls
import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.effects.Outcome
import org.jetbrains.kotlin.effects.structure.effects.Returns
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

    // Operators
    fun visit(operator: Operator): T = visit(operator as EsNode)

    fun visit(imply: Imply): T = visit(imply as BinaryOperator)
    fun visit(binaryOperator: BinaryOperator): T = visit(binaryOperator as Operator)
    fun visit(unaryOperator: UnaryOperator): T = visit(unaryOperator as Operator)

    fun visit(equalOperator: Equal): T = visit(equalOperator as BinaryOperator)
    fun visit(or: Or): T = visit(or as BinaryOperator)
    fun visit(and: And): T = visit(and as BinaryOperator)
    fun visit(not: Not): T = visit(not as UnaryOperator)
    fun visit(isOperator: Is): T = visit(isOperator as UnaryOperator)

    // Effects
    fun visit(effect: Effect): T = visit(effect as EsNode)
    fun visit(calls: Calls): T = visit(calls as Effect)

    // Outcomes
    fun visit(outcome: Outcome): T = visit(outcome as EsNode)
    fun visit(returns: Returns): T = visit(returns as Outcome)
    fun visit(throws: EsThrows): T = visit(throws as Outcome)


}
