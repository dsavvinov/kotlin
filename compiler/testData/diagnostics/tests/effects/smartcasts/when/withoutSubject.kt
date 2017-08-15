// !DIAGNOSTICS: -INVISIBLE_MEMBER -INVISIBLE_REFERENCE
// !LANGUAGE: +ContractEffects

import kotlin.internal.*

@Returns(ConstantValue.TRUE)
fun isString(@IsInstance(String::class) x: Any?) = x is String

