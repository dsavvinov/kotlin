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

import org.jetbrains.kotlin.effectsystem.adapters.ValueIdsFactory
import org.jetbrains.kotlin.effectsystem.factories.*
import org.jetbrains.kotlin.effectsystem.impls.ESConstant
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtVisitor
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall
import org.jetbrains.kotlin.resolve.constants.CompileTimeConstant
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.resolve.descriptorUtil.parents
import org.jetbrains.kotlin.types.KotlinType

class ConstantsBuilder(val trace: BindingTrace) : KtVisitor<ESConstant?, Unit>() {
    private val CONSTANT_VALUE_FQN = FqName("kotlin.effects.dsl.ConstantValue")

    private val TRUE_SHORT_NAME = Name.identifier("TRUE")
    private val FALSE_SHORT_NAME = Name.identifier("FALSE")
    private val NULL_SHORT_NAME = Name.identifier("NULL")
    private val NOT_NULL_SHORT_NAME = Name.identifier("NOT_NULL")
    private val WILDCARD_SHORT_NAME = Name.identifier("WILDCARD")

    override fun visitKtElement(element: KtElement, data: Unit?): ESConstant? {
        val resolvedCall = element.getResolvedCall(trace.bindingContext) ?: return null
        val descriptor = resolvedCall.resultingDescriptor

        if (descriptor.parents.first().fqNameSafe != CONSTANT_VALUE_FQN) return null

        return when (descriptor.fqNameSafe.shortName()) {
            TRUE_SHORT_NAME -> true.lift()
            FALSE_SHORT_NAME -> false.lift()
            NULL_SHORT_NAME -> NULL_CONSTANT
            NOT_NULL_SHORT_NAME -> NOT_NULL_CONSTANT
            WILDCARD_SHORT_NAME -> UNKNOWN_CONSTANT
            else -> null
        }
    }

    override fun visitConstantExpression(expression: KtConstantExpression, data: Unit?): ESConstant? {
        val type: KotlinType = trace.getType(expression) ?: return null

        val compileTimeConstant: CompileTimeConstant<*>
                = trace.get(BindingContext.COMPILE_TIME_VALUE, expression) ?: return null
        val value: Any? = compileTimeConstant.getValue(type)
        return createConstant(ValueIdsFactory.idForConstant(value), value, type)
    }
}