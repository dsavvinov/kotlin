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

import kotlin.internal.ContractsDSL
import kotlin.internal.InlineOnly

@ContractsDSL
class ContractBuilder {
    @ContractsDSL fun returns(): Effect = EffectStub
    @ContractsDSL fun returns(value: Any?): Effect = EffectStub
    @ContractsDSL fun returnsNotNull(): Effect = EffectStub
    @ContractsDSL inline fun <R> callsInPlace(lambda: Function<R>, kind: InvocationKind = InvocationKind.UNKNOWN): Effect = EffectStub

    object EffectStub : Effect
}

@ContractsDSL
@InlineOnly
inline fun contract(builder: ContractBuilder.() -> Unit) = Unit