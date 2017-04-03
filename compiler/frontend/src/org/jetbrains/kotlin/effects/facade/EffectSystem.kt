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
import org.jetbrains.kotlin.effects.facade.adapters.CallTreeBuilder
import org.jetbrains.kotlin.effects.facade.adapters.buildCallTree
import org.jetbrains.kotlin.effects.structure.call.CtCall
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.effects.Outcome
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.visitors.collectDataFlowInfo
import org.jetbrains.kotlin.effects.visitors.evaluate
import org.jetbrains.kotlin.effects.visitors.flatten
import org.jetbrains.kotlin.effects.visitors.generateEffectSchema
import org.jetbrains.kotlin.effects.visitors.helpers.getOutcome
import org.jetbrains.kotlin.effects.visitors.helpers.print
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.psi.ValueArgument
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.DescriptorUtils
import org.jetbrains.kotlin.resolve.TypeResolver
import org.jetbrains.kotlin.resolve.calls.context.BasicCallResolutionContext
import org.jetbrains.kotlin.resolve.calls.model.*
import org.jetbrains.kotlin.resolve.calls.results.OverloadResolutionResultsImpl
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo
import org.jetbrains.kotlin.resolve.calls.smartcasts.DelegatingDataFlowInfo
import org.jetbrains.kotlin.types.KotlinType

/**
 * Entry-point of EffectSystem. Provides simple interface for compiler.
 */
object EffectSystem {

    fun <D : CallableDescriptor> computeEffectsForArguments(
            resolutionResults: OverloadResolutionResultsImpl<D>,
            resolutionContext: BasicCallResolutionContext,
            languageVersionSettings: LanguageVersionSettings,
            typeResolver: TypeResolver
    ): OverloadResolutionResultsImpl<D> {
        // Temporary: Ignore non single-result resolutions
        if (!resolutionResults.isSingleResult) return resolutionResults

        val resultingCall: MutableResolvedCall<D> = resolutionResults.resultingCall

        val resolutionUtils = EsResolutionContext(
                resolutionContext.trace.bindingContext,
                KtPsiFactory(resultingCall.call.callElement),
                typeResolver,
                resolutionContext.scope,
                DescriptorUtils.getContainingModule(resultingCall.candidateDescriptor),
                resolutionContext.trace
        )

        val resultingEs = resultingCall
                .buildCallTree(resolutionUtils)
                ?.evaluateEffectSchema(resolutionUtils) ?: return resolutionResults

        val effectsDFInfo = resultingEs.extractDataFlowInfoAt(EsReturns(Unknown), languageVersionSettings, resolutionContext, resultingCall)

        val newDFInfo = object : MutableDataFlowInfoForArguments(resultingCall.dataFlowInfoForArguments.resultInfo) {
            override fun updateInfo(valueArgument: ValueArgument, dataFlowInfo: DataFlowInfo)
                    = resultingCall.dataFlowInfoForArguments.updateInfo(valueArgument, dataFlowInfo)

            // Temporary: Don't overload DFI for each argument
            override fun getInfo(valueArgument: ValueArgument): DataFlowInfo
                    = resultingCall.dataFlowInfoForArguments.getInfo(valueArgument)

            override fun getResultInfo(): DataFlowInfo
                    = resultingCall.dataFlowInfoForArguments.resultInfo.and(effectsDFInfo)
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


    fun printES(call: CtCall, context: EsResolutionContext): String {
        val baseES = call.generateEffectSchema(context) ?: return "Can't build effect schema"

        val flatES = baseES.flatten()

        val evES = flatES.evaluate()

        val name = call.resolvedCall.resultingDescriptor.name
        return "Base es of $name: \n" +
               baseES.print() +
               "\n" +
               "Flat es of $name: \n" +
               flatES.print() +
               "\n" +
               "ES of $name: \n" +
               evES.print()
    }

    private fun CtCall.evaluateEffectSchema(resolutionContext: EsResolutionContext): EffectSchema?
            = this.generateEffectSchema(resolutionContext)?.flatten()?.evaluate()

    private fun EffectSchema.extractDataFlowInfoAt(
            outcome: Outcome,
            languageVersionSettings: LanguageVersionSettings,
            resolutionContext: BasicCallResolutionContext,
            resultingCall: MutableResolvedCall<*>
    ) : DataFlowInfo {
        var dataFlowInfo: DataFlowInfo = DelegatingDataFlowInfo()

        var isOutcomeFeasible = false
        for (clause in clauses) {
            val clauseOutcome = clause.getOutcome()
            if (clauseOutcome == null || outcome.followsFrom(clauseOutcome)) {
                isOutcomeFeasible = true
                dataFlowInfo = dataFlowInfo.and(clause.left.collectDataFlowInfo(dataFlowInfo, languageVersionSettings))
            }
        }

        if (!isOutcomeFeasible) {
            resolutionContext.trace.record(BindingContext.CALL_EFFECTS_INFO, resultingCall.call, CallEffectsInfo(false))
        }
        return dataFlowInfo
    }
}



object Unknown : EsNode