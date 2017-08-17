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

interface ContractDescriptionTreeVisitor<out R, in D> {
    fun visitNode(node: ContractNode, data: D): R

    fun visitRoot(root: ContractRoot, data: D): R = visitNode(root, data)
    fun visitBlock(block: ContractBlock, data: D): R = visitNode(block, data)
    fun visitClause(clause: ContractClause, data: D): R = visitNode(clause, data)
    fun visitEffect(effectDeclaration: ContractEffect, data: D): R = visitNode(effectDeclaration, data)

    fun visitOperator(operator: ContractOperator, data: D): R = visitNode(operator, data)
    fun visitIs(isOperator: ContractIs, data: D): R = visitOperator(isOperator, data)
    fun visitAnd(andOperator: ContractAnd, data: D): R = visitOperator(andOperator, data)
    fun visitOr(orOperator: ContractOr, data: D): R = visitOperator(orOperator, data)
    fun visitNot(notOperator: ContractNot, data: D): R = visitOperator(notOperator, data)

    fun visitValue(value: ContractValue, data: D): R = visitNode(value, data)
    fun visitType(type: ContractType, data: D): R = visitNode(type, data)
}