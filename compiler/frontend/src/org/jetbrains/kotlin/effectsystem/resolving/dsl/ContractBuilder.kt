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

import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.effectsystem.functors.AndFunctor
import org.jetbrains.kotlin.effectsystem.functors.IsFunctor
import org.jetbrains.kotlin.effectsystem.functors.NotFunctor
import org.jetbrains.kotlin.effectsystem.functors.OrFunctor
import org.jetbrains.kotlin.effectsystem.impls.*
import org.jetbrains.kotlin.effectsystem.resolving.utility.toESVariable
import org.jetbrains.kotlin.effectsystem.structure.ESBooleanExpression
import org.jetbrains.kotlin.effectsystem.structure.ESClause
import org.jetbrains.kotlin.effectsystem.structure.KtContract
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.utils.addIfNotNull

internal class ContractBuilder(val trace: BindingTrace, val ownerDescriptor: FunctionDescriptor) {
    // Users should call this method as entrypoint instead of visitRoot,
    // because it is the only place where ContractRoot node is allowed
    fun buildFromTree(root: ContractRoot): KtContract? {
        val block = root.childs.firstOrNull() as? ContractBlock ?: return null
        return buildFromBlock(block, Unit)
    }

    private fun buildFromBlock(block: ContractBlock, data: Unit): KtContract {
        val clauses = mutableListOf<ESClause>()

        for (child in block.childs) {
            if (child !is ContractClause) {
                child.report("each statement of contract block should be implies-clause")
                continue
            }
            clauses.addIfNotNull(buildClause(child))
        }

        return EffectSchemaImpl(clauses, ownerDescriptor.valueParameters.map { it.toESVariable() })
    }

    private fun buildClause(clause: ContractClause): ESClause? {
        if (clause.childs.size != 2) return null
        val effect = clause.childs[0] as? ContractEffect ?: return null

        val condition = clause.childs[1].toESBooleanExpression() ?: return null

    }

    override fun visitClause(clause: ContractClause, data: Unit): KtContract {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitEffect(effectDeclaration: ContractEffect, data: Unit): KtContract {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitOperator(operator: ContractOperator, data: Unit): KtContract {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitValue(value: ContractValue, data: Unit): KtContract {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitType(type: ContractType, data: Unit): KtContract {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun ContractNode.report(message: String): Nothing? {
        trace.report(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(this.sourceElement, message))
        return null
    }

    private fun ContractNode.toESBooleanExpression() {
        this.accept(object: ContractDescriptionTreeVisitor<ESBooleanExpression?, Unit> {
            override fun visitNode(node: ContractNode, data: Unit): ESBooleanExpression? =
                    node.report("conclusion of statement should be boolean condition")

            override fun visitOperator(operator: ContractOperator, data: Unit): ESBooleanExpression? {
                val args = operator.childs.mapNotNull { it.accept(this, data) }

                val first = args.firstOrNull()
                val second = args.getOrNull(1)

                when (operator.operatorType) {
                    OperatorType.AND -> ESAnd(first ?: return null, second ?: return null, AndFunctor())
                    OperatorType.OR -> ESOr(first ?: return null, second ?: return null, OrFunctor())
                    OperatorType.NOT -> ESNot(first ?: return null, NotFunctor())
                    OperatorType.IS -> ESIs(first ?: return null, IsFunctor((operator.childs.getOrNull(1) as? ContractType)?.type ?: return null, false)
                    OperatorType.NOT_IS -> TODO()
                    OperatorType.EQUAL -> TODO()
                    OperatorType.NOT_EQUAL -> TODO()
                }
            }

            override fun visitValue(value: ContractValue, data: Unit): ESBooleanExpression {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun visitType(type: ContractType, data: Unit): ESBooleanExpression {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }, Unit)
    }
}