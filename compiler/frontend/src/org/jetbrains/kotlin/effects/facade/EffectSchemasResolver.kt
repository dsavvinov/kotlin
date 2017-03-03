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

import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.effects.facade.dsl.defineSchema
import org.jetbrains.kotlin.effects.structure.EsBoolean
import org.jetbrains.kotlin.effects.structure.EsInt
import org.jetbrains.kotlin.effects.structure.EsString
import org.jetbrains.kotlin.effects.structure.effects.Returns
import org.jetbrains.kotlin.effects.structure.general.EsFunction
import org.jetbrains.kotlin.effects.structure.general.EsType
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.lift
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.operators.Equal
import org.jetbrains.kotlin.effects.structure.schema.operators.Not
import org.jetbrains.kotlin.effects.structure.schema.operators.and

object EffectSchemasResolver {
    fun getEffectSchema(function: EsFunction): EffectSchema = kludgeFunctions[function] ?: throw IllegalArgumentException("Error: Effect Schema for function $function is not defined")
}

/**
 * Dirty hacks to get minimal working example in the absence of user-defined schemas
 * Used for launching following code:
 *
 *  ```
 *  fun foo() : Int = 42
 *
 *  fun bar(s: String, x: Int) : Int {
 *      return Integer.parseInt(s)
 *  }
 *
 *  fun test(x: Int, y: Int, z: String, w: Boolean, q: Boolean, p: Int) : Boolean {
 *      return x == y && z == "" && w
 *  }
 *
 *  object Main {
 *      @JvmStatic
 *      fun main(args: Array<String>) {
 *          val t = true
 *          val f = false
 *          val res = test(foo(), bar("42", foo()), "hi", t == f, t is Boolean, 42);
 *          println(res)
 *      }
 *  }
 *  ```
 */


val fooFunction = EsFunction("foo", listOf(), EsType(DefaultBuiltIns.Instance.intType))
val fooSchema = fooFunction.defineSchema {
    true.lift() to Returns(42.lift())
}

val barX = EsVariable("s", EsString)
val barY = EsVariable("x", EsInt)
val barFunction = EsFunction("bar", listOf(barX, barY), EsInt)
val barSchema = barFunction.defineSchema {
    true.lift() to Returns(13.lift())
}

val testX = EsVariable("x", EsInt)
val testY = EsVariable("y", EsInt)
val testZ = EsVariable("z", EsString)
val testW = EsVariable("w", EsBoolean)
val testQ = EsVariable("q", EsBoolean)
val testP = EsVariable("p", EsInt)
val testFunction = EsFunction("test", listOf(testX, testY, testZ, testW, testQ, testP), EsBoolean)
val testSchema = testFunction.defineSchema {
    Equal(testX, testY) and Equal(testZ, "".lift()) and Equal(testW, true.lift())        to Returns(true.lift())
    Not(Equal(testX, testY) and Equal(testZ, "".lift()) and Equal(testW, true.lift()))   to Returns(false.lift())
}

val kludgeFunctions = mapOf(
        fooFunction to fooSchema,
        barFunction to barSchema,
        testFunction to testSchema
)
