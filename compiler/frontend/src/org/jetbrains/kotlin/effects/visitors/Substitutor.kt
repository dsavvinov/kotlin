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

import org.jetbrains.kotlin.effects.facade.EsResolutionContext
import org.jetbrains.kotlin.effects.structure.effects.EsCallsEffect
import org.jetbrains.kotlin.effects.structure.effects.EsHints
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.general.*
import org.jetbrains.kotlin.effects.structure.schema.*
import org.jetbrains.kotlin.effects.structure.schema.operators.BinaryOperator
import org.jetbrains.kotlin.effects.structure.schema.operators.Imply
import org.jetbrains.kotlin.effects.structure.schema.operators.UnaryOperator
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValueFactory
import org.jetbrains.kotlin.resolve.calls.smartcasts.IdentifierInfo

/**
 * Visits EffectSchema-tree and substitutes every occurrence
 * of a variable from a given set with corresponding node.
 *
 * Generally, used to bind call-arguments to formal arguments.
 */
class Substitutor(val substs: Map<EsVariable, EsNode>, val correspondingCall: ResolvedCall<*>,
                  val mentionedCallables: Set<DataFlowValue>, val esResolutionContext: EsResolutionContext) : SchemaVisitor<EsNode> {
    override fun visit(node: EsNode): EsNode = node

    override fun visit(schema: EffectSchema): EsNode {
        val substitutedEffects = schema.clauses.map { it.accept(this) as Imply }
        return EffectSchema(substitutedEffects)
    }

    override fun visit(binaryOperator: BinaryOperator): EsNode =
            binaryOperator.newInstance(binaryOperator.left.accept(this), binaryOperator.right.accept(this))

    override fun visit(unaryOperator: UnaryOperator): EsNode =
            unaryOperator.newInstance(unaryOperator.arg.accept(this))

    override fun visit(variable: EsVariable): EsNode = substs[variable] ?: variable

    override fun visit(cons: Cons): Cons {
        val head = cons.head.accept(this)
        val tail = cons.tail.accept(this) as NodeSequence

        return Cons(head, tail)
    }

    override fun visit(nil: Nil): Nil = Nil

    override fun visit(esCallsEffect: EsCallsEffect): EsCallsEffect {
        substs.forEach { variable, substitution ->
            val count = esCallsEffect.callCounts[variable]
            if (count != null) {
                esCallsEffect.callCounts.remove(variable)
                esCallsEffect.callCounts.put(substitution as EsVariable, count)
            }
        }

        mentionedCallables.forEach { esCallsEffect.callCounts.putIfAbsent(EsVariable(it), EsCallsEffect.DEFAULT_CALL_COUNT) }
        esCallsEffect.bindToCall(correspondingCall)
        return esCallsEffect
    }

    override fun visit(esHints: EsHints): EsNode {
        val substitutedMap = esHints.types.map { (subject, setOfTypeExpressions) ->
            val substitutedTypeExpressions = setOfTypeExpressions.map { it.accept(this) }.toMutableSet()
            val substitutedSubject = if (subject is EsVariable) substs[subject] ?: subject else subject
            (substitutedSubject to substitutedTypeExpressions)
        }.toMap().toMutableMap()

        return EsHints(substitutedMap)
    }

    override fun visit(esReturns: EsReturns): EsNode = EsReturns(esReturns.value.accept(this))

    override fun visit(esCall: EsCall): EsNode {
        val callable = (esCall.callable as EsVariable)
        val callableSubstitution = substs[callable] as EsLambda // substitution for callable parameter should be lambda

        /** Мы хотим написать здесь примерно следующее:
         * lambdaSubstitution - это абстрактное описание лямбды (анонимная функция)
         * Точно также, как в bind(), мы хотим взять lambdaSubstituion.args, построить substs
         * используя esCall.args и отдать это все в новый инстанс биндера, который выдаст нам EffectSchema
         * соответствующую этому коллу
         */

        val lambdaDescriptor = callableSubstitution.descriptor

        // abstract parameters of passed lambda, like 'x' in `foo(24, { x -> x is String })`
        val parametersDescriptors = lambdaDescriptor.valueParameters
        // TODO: check if it consistent with how we produce dfv's in lambda's body in CallTreeBuilder
        val idInfos = parametersDescriptors.map { IdentifierInfo.Variable(it, DataFlowValue.Kind.STABLE_VALUE, null) }
        val dataFlowValues = idInfos.zip(parametersDescriptors)
                .map { (idInfo, parameterDescriptor) -> DataFlowValue(idInfo, parameterDescriptor.type) }
                .map { EsVariable(it) }

        val substs = dataFlowValues
                .zip(esCall.arguments)
                .toMap()
                .toMutableMap()


        // TODO: think about RESULT. From a brief glance it looks like it shouldn' appear in lambdas bodies, but who knows...

        // TODO: we shouldn't really pass correspondingCall here, but we can't pass anything else, as we don't really have resolvedCall
        val lambdaBodySubstitutor = Substitutor(substs, correspondingCall, setOf(), esResolutionContext)

        /** Note composition of substitutions:
         *  - first is defined by passing the function parameters to passed lambda
         *  - second is defined by passing the call arguments to the whole effect-schema
         *
         *  Example:
         *
         *  fun strangeIs(value: Any?, block: (Any?) -> Boolean) { ... } - signature
         *  true -> Hints(RESULT, (block(value) at Returns(true)) typeOf value) - Effect Schema
         *  strangeIs(q, { x -> x is String }) - call
         *
         *  So first substitution is given from call in Effect Schema: `block(value)` and substitutes abstract parameter of ES (value)
         *  instead of lambda's abstract parameter (x): { value is String }
         *  The second substitution is given from call at call-site and maps `value` to `q`.
         */
        val lamdaBaseSchema = callableSubstitution.bodySchema?.accept(lambdaBodySubstitutor) ?: EffectSchema(listOf())
        return lamdaBaseSchema.accept(this)
    }

    // Type arguments of Generic-type may be type-expressions
    override fun visit(esGenericType: EsGenericType): EsGenericType {
        val typeParams = esGenericType.typeParameters.map { it.accept(this) }
        return EsGenericType(esGenericType.bareTypeName, typeParams, esGenericType.resolutionContext)
    }
}

fun EffectSchema.bind(resolvedCall: ResolvedCall<*>, args: List<EsNode>,
                      mentionedCallables: Set<DataFlowValue>, esResolutionContext: EsResolutionContext) : EffectSchema? {
    val parametersDescriptors = resolvedCall.resultingDescriptor.valueParameters

    val idInfos = parametersDescriptors.map { IdentifierInfo.Variable(it, DataFlowValue.Kind.STABLE_VALUE, null) }
    val dataFlowValues = idInfos.zip(parametersDescriptors)
            .map { (idInfo, parameterDescriptor) -> DataFlowValue(idInfo, parameterDescriptor.type) }
            .map { EsVariable(it) }

    val substs = dataFlowValues.zip(args).toMap().toMutableMap()
    substs[EsVariable.RESULT] = EsVariable(DataFlowValueFactory.createDataFlowValue(
            resolvedCall.call.callElement as? KtCallExpression ?: return null,
            resolvedCall.resultingDescriptor.returnType ?: return null,
            esResolutionContext.context,
            esResolutionContext.moduleDescriptor
    ))

    val substitutor = Substitutor(substs, resolvedCall, mentionedCallables, esResolutionContext)
    return substitutor.visit(this) as EffectSchema
}