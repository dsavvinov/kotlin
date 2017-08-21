// !LANGUAGE: +ContractEffects

import kotlin.effects.dsl.*

fun Any?.isNull(): Boolean {
    contract {
        returns(ConstantValue.FALSE) implies (this@isNull != null)
    }
    return this == null
}

fun smartcastOnReceiver(x: Int?) {
    if (x.isNull()) {
        x<!UNSAFE_CALL!>.<!>inc()
    } else {
        <!DEBUG_INFO_SMARTCAST!>x<!>.dec()
    }
}

class UnstableReceiver {
    var x: Int? = 42

    fun smartcastOnUnstableReceiver() {
        if (x.isNull()) {
            x<!UNSAFE_CALL!>.<!>inc()
        } else {
            <!SMARTCAST_IMPOSSIBLE!>x<!>.dec()
        }
    }
}

