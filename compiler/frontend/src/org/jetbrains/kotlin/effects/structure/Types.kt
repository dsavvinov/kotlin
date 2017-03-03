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
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsType

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