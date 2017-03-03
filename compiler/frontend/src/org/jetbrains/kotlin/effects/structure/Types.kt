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

package org.jetbrains.kotlin.effects.structure

import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.effects.facade.Unknown
import org.jetbrains.kotlin.effects.facade.dsl.EffectSchemaBuilder
import org.jetbrains.kotlin.effects.facade.dsl.defineSchema
import org.jetbrains.kotlin.effects.structure.effects.Returns
import org.jetbrains.kotlin.effects.structure.general.*
import org.jetbrains.kotlin.effects.structure.schema.operators.Equal
import org.jetbrains.kotlin.effects.structure.schema.operators.Is
import org.jetbrains.kotlin.effects.structure.schema.operators.Not
import org.jetbrains.kotlin.effects.structure.schema.operators.and

/**
 * Kotlin types lifted into Effect System.
 * Probably, this file is temporary and will removed as Effect System
 * will be integrated with compiler (then, EsType will be changed into KtType).
 */

val EsBoolean = EsType(DefaultBuiltIns.Instance.boolean.defaultType)
fun (Boolean).lift(): EsConstant = EsConstant(this, EsBoolean)

val EsAnyNull = EsType(DefaultBuiltIns.Instance.nullableAnyType)

val EsInt = EsType(DefaultBuiltIns.Instance.intType)
fun (Int).lift(): EsConstant = EsConstant(this, EsInt)

val EsString = EsType(DefaultBuiltIns.Instance.stringType)
fun (String).lift() : EsConstant = EsConstant(this, EsString)

/** Intrinsics **/
val notArg = EsVariable("arg", EsBoolean)
val NotFunction = EsFunction("not", listOf(notArg), EsBoolean)

val eqLArg = EsVariable("left", EsAnyNull)
val eqRArg = EsVariable("right", EsAnyNull)
val eqFunction = EsFunction("==", listOf(eqLArg, eqRArg), EsBoolean)

val neqLArg = EsVariable("left", EsAnyNull)
val neqRArg = EsVariable("right", EsAnyNull)
val neqFunction = EsFunction("!=", listOf(neqLArg, neqRArg), EsBoolean)

val isLArg = EsVariable("left", EsAnyNull)
val isRArg = EsVariable("right", EsAnyNull)
val isFunction = EsFunction("is", listOf(isLArg, isRArg), EsBoolean)

val notIsLArg = EsVariable("left", EsAnyNull)
val notIsRArg = EsVariable("right", EsAnyNull)
val notIsFunction = EsFunction("!is", listOf(notIsLArg, notIsRArg), EsBoolean)

//val EsUnit = EsType("Unit")
//val unit = EsConstant(EsUnit, "unit")

//val EsAnyNull = EsType("Any?")

val intrinsicFunctions = mapOf<EsFunction, (List<EsNode>) -> EsNode >(
        eqFunction    to { l -> Equal(l[0], l[1]) },
        neqFunction   to { l -> Not(Equal(l[0], l[1])) },
        isFunction    to { l -> Is(l[0], l[1] as EsType) },
        notIsFunction to { l -> Not(Is(l[0], l[1] as EsType)) }
)


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
    true.lift() to Returns(42.lift(), EsInt)
}

val barX = EsVariable("s", EsString)
val barY = EsVariable("x", EsInt)
val barFunction = EsFunction("bar", listOf(barX, barY), EsInt)
val barSchema = barFunction.defineSchema {
    true.lift() to Returns(13.lift(), EsInt)
}

val testX = EsVariable("x", EsInt)
val testY = EsVariable("y", EsInt)
val testZ = EsVariable("z", EsString)
val testW = EsVariable("w", EsBoolean)
val testQ = EsVariable("q", EsBoolean)
val testP = EsVariable("p", EsInt)
val testFunction = EsFunction("test", listOf(testX, testY, testZ, testW, testQ, testP), EsBoolean)
val testSchema = testFunction.defineSchema {
    Equal(testX, testY) and Equal(testZ, "".lift()) and Equal(testW, true.lift())        to Returns(true.lift(), EsBoolean)
    Not(Equal(testX, testY) and Equal(testZ, "".lift()) and Equal(testW, true.lift()))   to Returns(false.lift(), EsBoolean)
}


val kludgeFunctions = mapOf<EsFunction, (List<EsNode>) -> EsNode>(
        fooFunction to { l -> fooSchema },
        barFunction to { l -> barSchema },
        testFunction to { l -> testSchema }
)