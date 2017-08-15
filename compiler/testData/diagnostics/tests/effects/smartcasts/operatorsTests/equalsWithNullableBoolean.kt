// !DIAGNOSTICS: -INVISIBLE_MEMBER -INVISIBLE_REFERENCE
// !LANGUAGE: +ContractEffects

import kotlin.internal.*

@Returns(ConstantValue.TRUE)
fun safeIsString(@IsInstance(String::class) x: Any?): Boolean? = x?.let { it is String }

fun equalsTrue(x: Any?) {
    if (safeIsString(x) == true) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
    } else {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    }
}

fun equalsFalse(x: Any?) {
    if (safeIsString(x) == false) {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    } else {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    }
}

fun equalsNull(x: Any?) {
    if (safeIsString(x) == null) {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    }
    else {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    }
}

fun notEqualsTrue(x: Any?) {
    if (safeIsString(x) != true) {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    } else {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
    }
}

fun notEqualsFalse(x: Any?) {
    if (safeIsString(x) != false) {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    } else {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    }
}

fun notEqualsNull(x: Any?) {
    if (safeIsString(x) != null) {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    } else {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    }
}