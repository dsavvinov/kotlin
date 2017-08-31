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

package org.jetbrains.kotlin.effectsystem.interpretation

import org.jetbrains.kotlin.descriptors.contracts.expressions.ConstantDescriptor
import org.jetbrains.kotlin.descriptors.contracts.expressions.Constants
import org.jetbrains.kotlin.effectsystem.factories.NOT_NULL_CONSTANT
import org.jetbrains.kotlin.effectsystem.factories.UNKNOWN_CONSTANT
import org.jetbrains.kotlin.effectsystem.factories.lift
import org.jetbrains.kotlin.effectsystem.impls.ESConstant

internal class ConstantValuesInterpreter {
    fun interpretConstant(constantDescriptor: ConstantDescriptor): ESConstant? = when (constantDescriptor.constantValue) {
        Constants.TRUE -> true.lift()
        Constants.FALSE -> false.lift()
        Constants.NULL -> null.lift()
        Constants.NOT_NULL -> NOT_NULL_CONSTANT
        Constants.WILDCARD -> UNKNOWN_CONSTANT
        else -> null
    }
}