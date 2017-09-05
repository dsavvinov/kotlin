// !LANGUAGE: +ContractEffects

import kotlin.effects.dsl.*

fun test(x: Any?) {
    if (isString(x)) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
    }
}

fun isString(x: Any?): Boolean {
    contract {
        returns(true) implies (x is String)
    }
    return x is String
}


