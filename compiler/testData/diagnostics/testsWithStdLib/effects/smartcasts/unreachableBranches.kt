// !LANGUAGE: +ContractEffects

import kotlin.effects.dsl.*

// ===== Definitions ====
fun isString(x: Any?): Boolean {
    contract {
        returns(ConstantValue.TRUE) implies (x is String)
    }
    return x is String
}

fun notIsString(x: Any?): Boolean {
    contract {
        returns(ConstantValue.FALSE) implies (x is String)
    }
    return x !is String
}

fun notIsInt(x: Any?): Boolean {
    contract {
        returns(ConstantValue.FALSE) implies (x is String)
    }
    return x !is Int
}




// ==== Actual tests =======

fun implicitAlwaysFalse(x: Any?) {
    if (isString(x) && !isString(x)) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
    } else {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    }
}

fun implicitAlwaysFalseSpilling(x: Any?) {
    if (isString(x) && !isString(x)) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
    }
    x.<!UNRESOLVED_REFERENCE!>length<!>
}