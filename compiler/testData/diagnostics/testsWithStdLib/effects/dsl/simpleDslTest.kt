// !LANGUAGE: +ContractEffects

import kotlin.effects.dsl.*

fun test(x: Any?) {
    if (isString(x)) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
    }
}

fun isString(x: Any?): Boolean {
    contract {
        returns(ConstantValue.TRUE) implies (x is String)
    }
    return x is String
}


