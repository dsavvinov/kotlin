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

import org.jetbrains.kotlin.effectsystem.structure.ESValue
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.types.KotlinType

interface ContractNode {
    val childs: List<ContractNode>
    val sourceElement: KtElement // mainly for reporting diagnostics

    fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R
}

class ContractRoot(override val sourceElement: KtElement, override val childs: List<ContractNode>) : ContractNode {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitRoot(this, data)
}

class ContractBlock(override val sourceElement: KtElement, override val childs: List<ContractNode>) : ContractNode {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitBlock(this, data)
}

class ContractClause(override val sourceElement: KtElement, override val childs: List<ContractNode>) : ContractNode {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitClause(this, data)
}

class ContractEffect(val effectType: EffectType, override val sourceElement: KtElement, override val childs: List<ContractNode>) : ContractNode {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitEffect(this, data)
}

interface ContractOperator : ContractNode {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitOperator(this, data)
}

class ContractIs(val isNegated: Boolean, override val sourceElement: KtElement, override val childs: List<ContractNode>) : ContractOperator {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitIs(this, data)
}

class ContractEqual(val isNegated: Boolean, override val sourceElement: KtElement, override val childs: List<ContractNode>) : ContractOperator {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitEquals(this, data)
}

class ContractAnd(override val sourceElement: KtElement, override val childs: List<ContractNode>) : ContractOperator {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitAnd(this, data)
}

class ContractOr(override val sourceElement: KtElement, override val childs: List<ContractNode>) : ContractOperator {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitAnd(this, data)
}

class ContractNot(override val sourceElement: KtElement, override val childs: List<ContractNode>) : ContractOperator {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitAnd(this, data)
}


class ContractValue(val esValue: ESValue, override val sourceElement: KtElement, override val childs: List<ContractNode> = listOf()) : ContractNode {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitValue(this, data)
}

class ContractType(val type: KotlinType, override val sourceElement: KtElement, override val childs: List<ContractNode> = listOf()) : ContractNode {
    override fun <R, D> accept(visitor: ContractDescriptionTreeVisitor<R, D>, data: D): R = visitor.visitType(this, data)
}