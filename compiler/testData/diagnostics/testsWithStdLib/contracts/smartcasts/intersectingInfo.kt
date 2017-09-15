// !LANGUAGE: +ReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

import kotlin.internal.contracts.*

fun isString(x: Any?): Boolean {
    contract {
        returns(true) implies (x is String)
    }
    return x is String
}


fun notIsString(x: Any?): Boolean {
    contract {
        returns(false) implies (x is String)
    }
    return x !is String
}

fun notIsInt(x: Any?): Boolean {
    contract {
        returns(false) implies (x !is Int)
    }
    return x !is Int
}

fun intersectingInfo(x: Any?, y: Any?) {
    if ((isString(x) && y is String) || (!notIsString(x) && !notIsInt(y))) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
        y.<!UNRESOLVED_REFERENCE!>length<!>
        y.<!UNRESOLVED_REFERENCE!>inc<!>()
    }
    else {
        x.<!UNRESOLVED_REFERENCE!>length<!>
        y.<!UNRESOLVED_REFERENCE!>length<!>
        y.<!UNRESOLVED_REFERENCE!>inc<!>()
    }
}

fun intersectingInfo2(x: Any?, y: Any?) {
    // In each arg of "||"-operator presented fact "x is String" which should lead to smartcast.
    // Also there are 3 additional facts: "x is Int", "y is String", "y is Int". One
    // of them is absent in each arg of "||"-operator, so they *shouldn't* lead to smartcast

    if ((isString(x) && !notIsInt(x) && y is String) ||
        (!notIsString(x) && isString(y) && y is Int) ||
        (x is String && !notIsInt(y) && x is Int)) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
        x.<!UNRESOLVED_REFERENCE!>inc<!>()
        y.<!UNRESOLVED_REFERENCE!>length<!>
        y.<!UNRESOLVED_REFERENCE!>inc<!>()
    }
    x.<!UNRESOLVED_REFERENCE!>length<!>
    x.<!UNRESOLVED_REFERENCE!>inc<!>()
    y.<!UNRESOLVED_REFERENCE!>length<!>
    y.<!UNRESOLVED_REFERENCE!>inc<!>()
}

