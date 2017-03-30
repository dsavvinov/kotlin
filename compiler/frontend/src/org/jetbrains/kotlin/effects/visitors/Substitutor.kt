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

import org.jetbrains.kotlin.effects.facade.EsResolutionUtils
import org.jetbrains.kotlin.effects.structure.effects.EsCalls
import org.jetbrains.kotlin.effects.structure.general.EsFunction
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.*
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.Imply
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator
import org.jetbrains.kotlin.resolve.calls.model.DefaultValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.VarargValueArgument
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValueFactory
import org.jetbrains.kotlin.resolve.calls.smartcasts.IdentifierInfo

/**
 * Visits EffectSchema-tree and substitutes every occurrence
 * of a variable from a given set with corresponding node.
 *
 * Generally, used to bind call-arguments to formal arguments.
 */
class Substitutor(val substs: Map<DataFlowValue, EsNode>, val correspondingCall : ResolvedCall<*>) : SchemaVisitor<EsNode> {
    override fun visit(node: EsNode): EsNode = node

    override fun visit(schema: EffectSchema): EsNode {
        val substitutedEffects = schema.clauses.map { it.accept(this) as Imply }
        return EffectSchema(substitutedEffects)
    }

    override fun visit(binaryOperator: BinaryOperator): EsNode =
            binaryOperator.newInstance(binaryOperator.left.accept(this), binaryOperator.right.accept(this))

    override fun visit(unaryOperator: UnaryOperator): EsNode =
            unaryOperator.newInstance(unaryOperator.arg.accept(this))

    override fun visit(variable: EsVariable): EsNode = substs[variable.value] ?: variable

    override fun visit(cons: Cons): Cons {
        val head = cons.head.accept(this)
        val tail = cons.tail.accept(this) as NodeSequence

        return Cons(head, tail)
    }

    override fun visit(nil: Nil): Nil = Nil

    override fun visit(esCalls: EsCalls): EsCalls = esCalls.bindToCall(correspondingCall)
}

fun (EffectSchema).bind(resolvedCall: ResolvedCall<*>, args: List<EsNode>) : EffectSchema {
    val parametersDescriptors = resolvedCall.resultingDescriptor.valueParameters
    val idInfos = parametersDescriptors.map { IdentifierInfo.Variable(it, DataFlowValue.Kind.STABLE_VALUE, null) }
    val dataFlowValues = idInfos.zip(parametersDescriptors).map {
        (idInfo, parameterDescriptor) -> DataFlowValue(idInfo, parameterDescriptor.type)
    }

    val substs = dataFlowValues.zip(args).toMap()

    val substitutor = Substitutor(substs, resolvedCall)
    return substitutor.visit(this) as EffectSchema
}