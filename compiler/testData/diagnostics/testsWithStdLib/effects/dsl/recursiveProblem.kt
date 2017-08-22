// !DIAGNOSTICS: -NOTHING_TO_INLINE
// !LANGUAGE: +ContractEffects

import kotlin.effects.dsl.*

public inline fun require(value: Boolean): Unit {
    require(value) { "Failed requirement." }
}

public inline fun require(value: Boolean, lazyMessage: () -> Any): Unit {
    if (!value) {
        val message = lazyMessage()
        throw IllegalArgumentException(message.toString())
    }
}