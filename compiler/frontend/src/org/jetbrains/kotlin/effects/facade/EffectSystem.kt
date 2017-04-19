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

package org.jetbrains.kotlin.effects.facade

import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.effects.facade.adapters.buildCallTree
import org.jetbrains.kotlin.effects.structure.call.CallTree
import org.jetbrains.kotlin.effects.structure.call.CtCall
import org.jetbrains.kotlin.effects.structure.call.CtNode
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.effects.Outcome
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.lift
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.Term
import org.jetbrains.kotlin.effects.structure.schema.operators.EsEqual
import org.jetbrains.kotlin.effects.structure.schema.operators.EsIs
import org.jetbrains.kotlin.effects.visitors.collectDataFlowInfo
import org.jetbrains.kotlin.effects.visitors.reduce
import org.jetbrains.kotlin.effects.visitors.flatten
import org.jetbrains.kotlin.effects.visitors.generateEffectSchema
import org.jetbrains.kotlin.effects.visitors.helpers.getOutcome
import org.jetbrains.kotlin.effects.visitors.helpers.print
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.TypeResolutionContext
import org.jetbrains.kotlin.resolve.TypeResolver
import org.jetbrains.kotlin.resolve.calls.context.BasicCallResolutionContext
import org.jetbrains.kotlin.resolve.calls.model.MutableDataFlowInfoForArguments
import org.jetbrains.kotlin.resolve.calls.model.MutableResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.VariableAsFunctionResolvedCallImpl
import org.jetbrains.kotlin.resolve.calls.results.OverloadResolutionResultsImpl
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfoFactory
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValueFactory
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext
import org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor
import org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.ConditionalDataFlowInfo

/**
 * Entry-point of EffectSystem
 */
object EffectSystem {

    fun getConditionalEffectsInfo(
            expression: KtExpression?,
            expressionTypingContext: ExpressionTypingContext,
            typeResolver: TypeResolver,
            result: Boolean,
            languageVersionSettings: LanguageVersionSettings
    ): DataFlowInfo {
        expression ?: return DataFlowInfoFactory.EMPTY

        val resolutionContext = EsResolutionContext.create(
                expressionTypingContext,
                KtPsiFactory(expression),
                typeResolver
        )

        val resultingEs = expression
                .buildCallTree(resolutionContext)
                ?.computeEffectSchema(resolutionContext) as? EffectSchema ?: return DataFlowInfoFactory.EMPTY

        val effectsDataFlowInfo = resultingEs.extractDataFlowInfoAt(EsReturns(result.lift()))

        return effectsDataFlowInfo.toDataFlowInfo(languageVersionSettings)
    }

    fun <D : CallableDescriptor> computeEffectsForArguments(
            resolutionResults: OverloadResolutionResultsImpl<D>,
            resolutionContext: BasicCallResolutionContext,
            languageVersionSettings: LanguageVersionSettings,
            typeResolver: TypeResolver
    ): OverloadResolutionResultsImpl<D> {
        // Temporary: Ignore non single-result resolutions
        if (!resolutionResults.isSingleResult) return resolutionResults

        val resultingCall: MutableResolvedCall<D> = resolutionResults.resultingCall
        val esResolutionContext = EsResolutionContext.create(resolutionContext, KtPsiFactory(resultingCall.call.callElement), typeResolver)

        val callTree = (resultingCall.call.callElement as? KtExpression)?.buildCallTree(esResolutionContext)
        val resultingEs = callTree?.computeEffectSchema(esResolutionContext) ?: return resolutionResults

        val effectsDFInfo = resultingEs.extractDataFlowInfoAt(EsReturns(Unknown))

        resultingEs.checkAndRecordFeasibilty(EsReturns(Unknown), resolutionContext.trace, resultingCall.call)

        val newDFInfo = object : MutableDataFlowInfoForArguments(resultingCall.dataFlowInfoForArguments.resultInfo) {
            override fun updateInfo(valueArgument: ValueArgument, dataFlowInfo: DataFlowInfo)
                    = resultingCall.dataFlowInfoForArguments.updateInfo(valueArgument, dataFlowInfo)

            // Temporary: Don't overload DFI for each argument
            override fun getInfo(valueArgument: ValueArgument): DataFlowInfo
                    = resultingCall.dataFlowInfoForArguments.getInfo(valueArgument)

            override fun getResultInfo(): DataFlowInfo
                    = resultingCall.dataFlowInfoForArguments.resultInfo.and(effectsDFInfo.toDataFlowInfo(languageVersionSettings))
        }



        when (resultingCall) {
            is VariableAsFunctionResolvedCallImpl -> {
                val mutableResolvedCallWithEffectsInfo = object : MutableResolvedCall<FunctionDescriptor> by resultingCall.functionCall {
                    override fun getDataFlowInfoForArguments(): MutableDataFlowInfoForArguments = newDFInfo
                }

                val varAsFunctionCallWithEffectsInfo = VariableAsFunctionResolvedCallImpl(
                        mutableResolvedCallWithEffectsInfo,
                        resultingCall.variableCall
                )

                return OverloadResolutionResultsImpl.success(varAsFunctionCallWithEffectsInfo) as OverloadResolutionResultsImpl<D>
            }


            is MutableResolvedCall -> {
                val mutableResolvedCallWithEffectsInfo = object : MutableResolvedCall<D> by resultingCall {
                    override fun getDataFlowInfoForArguments(): MutableDataFlowInfoForArguments = newDFInfo
                }

                return OverloadResolutionResultsImpl.success(mutableResolvedCallWithEffectsInfo)
            }
        }

        return resolutionResults
    }


    fun printES(call: CallTree, context: EsResolutionContext): String {
        val baseES = call.generateEffectSchema(context) ?: return "Can't build effect schema"

        val flatES = baseES.flatten()

        val evES = flatES.reduce()

        val name = (call.root as? CtCall)?.resolvedCall?.resultingDescriptor?.name ?: "<unnamed>"
        return "Base es of $name: \n" +
               baseES.print() +
               "\n" +
               "Flat es of $name: \n" +
               flatES.print() +
               "\n" +
               "ES of $name: \n" +
               evES.print()
    }

    fun getInvocationsInfo(lambda: KtLambdaExpression?, call: KtCallExpression?, trace: BindingTrace,
                           typeResolver: TypeResolver? = null): MutableEffectsInfo.InvocationsInfo? {
        lambda ?: return null
        call ?: return null
        typeResolver ?: return null

        val esResolutionContext = EsResolutionContext.create(trace, call, typeResolver) ?: return null

        val resultingEs = call.buildCallTree(esResolutionContext)?.computeEffectSchema(esResolutionContext) ?: return null
        val effectsInfo = resultingEs.extractDataFlowInfoAt(EsReturns(Unknown))

        val lambdaDFV = DataFlowValueFactory.createDataFlowValue(lambda, trace.getType(lambda) ?: return null, trace.bindingContext, esResolutionContext.moduleDescriptor)
        return effectsInfo.getInvocationsInfo(lambdaDFV)
    }

    private fun CallTree.computeEffectSchema(resolutionContext: EsResolutionContext): EffectSchema?
            = this.generateEffectSchema(resolutionContext)?.flatten()?.reduce()

    private fun Term.evaluate(resolutionContext: EsResolutionContext): EffectSchema?
            = this.castToSchema().flatten().reduce()

    private fun EffectSchema.checkAndRecordFeasibilty(
            outcome: Outcome,
            bindingTrace: BindingTrace,
            reportOn: Call
    ) {
        for (clause in clauses) {
            val clauseOutcome = clause.getOutcome()
            if (clauseOutcome == null || outcome.followsFrom(clauseOutcome)) {
                return
            }
        }

        bindingTrace.record(BindingContext.CALL_EFFECTS_INFO, reportOn, CallEffectsInfo(false))
    }

    private fun EffectSchema.extractDataFlowInfoAt(
            outcome: Outcome
    ) : MutableEffectsInfo {
        val effectsInfo = MutableEffectsInfo()

        for (clause in clauses) {
            val clauseOutcome = clause.getOutcome()
            if (clauseOutcome == null || outcome.followsFrom(clauseOutcome)) {
                clause.collectDataFlowInfo(effectsInfo)
            }
        }

        return effectsInfo
    }

    fun getWhenEntryEffects(subjectExpression: KtExpression?, condition: KtWhenConditionIsPattern,
                            typingContext: ExpressionTypingContext, typeResolver: TypeResolver,
                            languageVersionSettings: LanguageVersionSettings): ConditionalDataFlowInfo {
        subjectExpression ?: return ConditionalDataFlowInfo.EMPTY

        val esResolutionContext = EsResolutionContext.create(typingContext, KtPsiFactory(subjectExpression), typeResolver)

        val typeResolutionContext = TypeResolutionContext(typingContext.scope, typingContext.trace,
                                                          true, /*allowBareTypes=*/ true, typingContext.isDebuggerContext)
        val possiblyBareType = typeResolver.resolvePossiblyBareType(typeResolutionContext, condition.typeReference ?: return ConditionalDataFlowInfo.EMPTY)
        val typeAfterIs = possiblyBareType.actualType

        val subjectSchema = subjectExpression.buildCallTree(esResolutionContext)?.computeEffectSchema(esResolutionContext)
                            ?: return ConditionalDataFlowInfo.EMPTY

        // Effects of Is-entry equivalent to effects of expression `subjectExpression is typeAfterIs`
        val entrySchema = EsIs(subjectSchema, typeAfterIs).flatten().reduce() ?: return ConditionalDataFlowInfo.EMPTY

        val trueDFI = entrySchema.extractDataFlowInfoAt(EsReturns(true.lift())).toDataFlowInfo(languageVersionSettings)
        val falseDFI = entrySchema.extractDataFlowInfoAt(EsReturns(false.lift())).toDataFlowInfo(languageVersionSettings)

        return ConditionalDataFlowInfo(trueDFI, falseDFI)
    }

    fun getWhenEntryEffects(subjectExpression: KtExpression?, condition: KtWhenConditionWithExpression,
                            typingContext: ExpressionTypingContext, typeResolver: TypeResolver,
                            languageVersionSettings: LanguageVersionSettings): ConditionalDataFlowInfo {
        subjectExpression ?: return ConditionalDataFlowInfo.EMPTY

        val esResolutionContext = EsResolutionContext.create(typingContext, KtPsiFactory(subjectExpression), typeResolver)

        val subjectSchema = subjectExpression.buildCallTree(esResolutionContext)?.computeEffectSchema(esResolutionContext)
                            ?: return ConditionalDataFlowInfo.EMPTY
        val conditionSchema = condition.expression?.buildCallTree(esResolutionContext)?.computeEffectSchema(esResolutionContext)
                              ?: return ConditionalDataFlowInfo.EMPTY

        // Effects of when-condition with expression equivalent to effects of `subjectExpression == condition.expression`
        val entrySchema = EsEqual(subjectSchema, conditionSchema).flatten().reduce() ?: return ConditionalDataFlowInfo.EMPTY

        val trueDFI = entrySchema.extractDataFlowInfoAt(EsReturns(true.lift()))
        val falseDFI = entrySchema.extractDataFlowInfoAt(EsReturns(false.lift()))

        return ConditionalDataFlowInfo(trueDFI.toDataFlowInfo(languageVersionSettings), falseDFI.toDataFlowInfo(languageVersionSettings))
    }

}



object Unknown : EsNode