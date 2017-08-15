// !DIAGNOSTICS: -INVISIBLE_MEMBER -INVISIBLE_REFERENCE
// !LANGUAGE: +ContractEffects

import kotlin.internal.*

@Returns(ConstantValue.TRUE)
fun isString(@IsInstance(String::class) x: Any?) = x is String

fun exhaustive(x: Any?) {
    when (isString(x)) {
        true -> <!DEBUG_INFO_SMARTCAST!>x<!>.length
        false -> x.<!UNRESOLVED_REFERENCE!>length<!>
    }

    when(!isString(x)) {
        true -> x.<!UNRESOLVED_REFERENCE!>length<!>
        false -> <!DEBUG_INFO_SMARTCAST!>x<!>.length
    }
}

fun smartcastInElse(x: Any?) {
    when (isString(x)) {
        false -> x.<!UNRESOLVED_REFERENCE!>length<!>
        else -> <!DEBUG_INFO_SMARTCAST!>x<!>.length
    }

    when (!isString(x)) {
        true -> x.<!UNRESOLVED_REFERENCE!>length<!>
        else -> <!DEBUG_INFO_SMARTCAST!>x<!>.length
    }
}

@Returns(ConstantValue.TRUE)
fun safeIsString(@IsInstance(String::class) x: Any?): Boolean? = x?.let { it is String }

fun elseWithNullableResult(x: Any?) {
    when (safeIsString(x)) {
        false -> x.<!UNRESOLVED_REFERENCE!>length<!>
        else -> x.<!UNRESOLVED_REFERENCE!>length<!>
    }

    when (safeIsString(x)) {
        true -> <!DEBUG_INFO_SMARTCAST!>x<!>.length
        else -> x.<!UNRESOLVED_REFERENCE!>length<!>
    }

    when (safeIsString(x)) {
        true -> <!DEBUG_INFO_SMARTCAST!>x<!>.length
        false -> x.<!UNRESOLVED_REFERENCE!>length<!>
        else -> x.<!UNRESOLVED_REFERENCE!>length<!>
    }

    when (safeIsString(x)) {
        true -> <!DEBUG_INFO_SMARTCAST!>x<!>.length
        null -> x.<!UNRESOLVED_REFERENCE!>length<!>
        else -> x.<!UNRESOLVED_REFERENCE!>length<!>
    }
}

fun exhaustiveWithNullableResult(x: Any?) {
    when (safeIsString(x)) {
        true -> <!DEBUG_INFO_SMARTCAST!>x<!>.length
        false -> x.<!UNRESOLVED_REFERENCE!>length<!>
        null -> x.<!UNRESOLVED_REFERENCE!>length<!>
    }

    when (safeIsString(x)) {
        false -> x.<!UNRESOLVED_REFERENCE!>length<!>
        true -> <!DEBUG_INFO_SMARTCAST!>x<!>.length
        null -> x.<!UNRESOLVED_REFERENCE!>length<!>
    }

    when (safeIsString(x)) {
        false -> x.<!UNRESOLVED_REFERENCE!>length<!>
        null -> x.<!UNRESOLVED_REFERENCE!>length<!>
        true -> <!DEBUG_INFO_SMARTCAST!>x<!>.length
    }
}