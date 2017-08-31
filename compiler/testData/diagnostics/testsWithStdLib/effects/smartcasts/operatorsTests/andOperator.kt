// !LANGUAGE: +ContractEffects

import kotlin.effects.dsl.*

fun trueWhenString(x: Any?): Boolean {
    contract {
        returns(true) implies (x is String)
    }
    return x is String
}

fun trueWhenInt(x: Any?): Boolean {
    contract {
        returns(true) implies (x is Int)
    }
    return x is Int
}

fun falseWhenString(x: Any?): Boolean {
    contract {
        returns(false) implies (x is String)
    }
    return x !is String
}

fun falseWhenInt(x: Any?): Boolean {
    contract {
        returns(false) implies (x is Int)
    }
    return x !is Int
}


// ==== Actual tests ====

fun truetrue(x: Any?) {
    if (trueWhenString(x) && trueWhenInt(x)) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
        <!DEBUG_INFO_SMARTCAST!>x<!>.inc()
    }
    x.<!UNRESOLVED_REFERENCE!>length<!>
    x.<!UNRESOLVED_REFERENCE!>inc<!>()
}

fun truefalse(x: Any?) {
    if (trueWhenString(x) && falseWhenInt(x)) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
        x.<!UNRESOLVED_REFERENCE!>inc<!>()
    } else {
        x.<!UNRESOLVED_REFERENCE!>length<!>
        x.<!UNRESOLVED_REFERENCE!>inc<!>()
    }
}

fun falsetrue(x: Any?) {
    if (falseWhenString(x) && trueWhenInt(x)) {
        x.<!UNRESOLVED_REFERENCE!>length<!>
        <!DEBUG_INFO_SMARTCAST!>x<!>.inc()
    } else {
        x.<!UNRESOLVED_REFERENCE!>length<!>
        x.<!UNRESOLVED_REFERENCE!>inc<!>()
    }
}

fun falsefalse(x: Any?) {
    if (falseWhenString(x) && falseWhenInt(x)) {
        x.<!UNRESOLVED_REFERENCE!>length<!>
        x.<!UNRESOLVED_REFERENCE!>inc<!>()
    } else {
        // Note that we can't argue that we have any of smartcasts here,
        // because we don't know which one of both arguments was 'false' to bring us here
        x.<!UNRESOLVED_REFERENCE!>length<!>
        x.<!UNRESOLVED_REFERENCE!>inc<!>()
    }
}