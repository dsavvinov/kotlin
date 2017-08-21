// !LANGUAGE: +ContractEffects

import kotlin.effects.dsl.*

fun myEqualsNull(x: Int?): Boolean {
    contract {
        returns(ConstantValue.FALSE) implies (x != null)
    }
    return x == null
}

fun myEqualsNotNull(x: Int?): Boolean {
    contract {
        returns(ConstantValue.TRUE) implies (x != null)
    }
    return x != null
}

fun testBasicEquals(x: Int?) {
    x<!UNSAFE_CALL!>.<!>inc()

    if (myEqualsNull(x)) {
        x<!UNSAFE_CALL!>.<!>inc()
    } else {
        <!DEBUG_INFO_SMARTCAST!>x<!>.inc()
    }

    x<!UNSAFE_CALL!>.<!>inc()
}

fun testBasicNotEquals(x: Int?) {
    x<!UNSAFE_CALL!>.<!>inc()

    if (myEqualsNotNull(x)) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.inc()
    } else {
        x<!UNSAFE_CALL!>.<!>inc()
    }

    x<!UNSAFE_CALL!>.<!>inc()
}

