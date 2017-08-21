// !LANGUAGE: +ContractEffects

import kotlin.effects.dsl.*

fun isString(x: Any?): Boolean {
    contract {
        returns(ConstantValue.TRUE) implies (x is String)
    }
    return x is String
}

fun incorrectPartDoesntMatter(x: Any?) {
    if (isString(x) && <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
    } else {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    }
}