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

import org.jetbrains.kotlin.effects.structure.call.CtCall
import org.jetbrains.kotlin.effects.structure.effects.Outcome
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.visitors.collect
import org.jetbrains.kotlin.effects.visitors.evaluate
import org.jetbrains.kotlin.effects.visitors.flatten
import org.jetbrains.kotlin.effects.visitors.generateEffectSchema
import org.jetbrains.kotlin.effects.visitors.helpers.getOutcome
import org.jetbrains.kotlin.effects.visitors.helpers.print
import org.jetbrains.kotlin.types.KotlinType

/**
 * Entry-point of EffectSystem. Provides simple interface for compiler.
 */
object EffectSystem {
    fun printES(call: CtCall, utils: EsResolutionUtils): String {
        val baseES = call.generateEffectSchema(utils)

        val flatES = baseES.flatten() as EffectSchema

        val evES = flatES.evaluate() as EffectSchema

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

    fun getInfo(call: CtCall, outcome: Outcome, utils: EsResolutionUtils): EsInfoHolder? {
        val basicEffectSchema = call.generateEffectSchema(utils)
        val flatEs = basicEffectSchema.flatten()
        val evaluatedEs = flatEs.evaluate() as EffectSchema

        return evaluatedEs.collectAt(outcome)
    }

    private fun (EffectSchema).collectAt(outcome: Outcome) : EsInfoHolder? {
        val varValues = mutableMapOf<EsVariable, EsConstant>()
        val varTypes = mutableMapOf<EsVariable, KotlinType>()

        var feasible = false
        for (clause in clauses) {
            val clauseOutcome = clause.getOutcome()
            if (clauseOutcome == null || outcome.followsFrom(clauseOutcome)) {
                feasible = true
                clause.left.collect(varValues, varTypes)
            }
        }

        // If at least one feasible clause was found, then return collected info
        // Otherwise, return null to indicate that requested Outcome is infeasible
        return if (feasible) EsInfoHolderImpl(varValues, varTypes) else null
    }
}

object Unknown : EsNode