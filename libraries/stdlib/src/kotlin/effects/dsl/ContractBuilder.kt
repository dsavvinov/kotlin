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

package kotlin.effects.dsl

class ContractBuilder {
    fun returns(): Effect = EffectStub
    fun returns(value: Any?): Effect = EffectStub
    fun returnsNotNull(): Effect = EffectStub
    inline fun <R> callsInPlace(lambda: Function<R>, kind: InvocationKind = InvocationKind.UNKNOWN): Effect = EffectStub

    object EffectStub : Effect
}

inline fun contract(builder: ContractBuilder.() -> Unit) = Unit