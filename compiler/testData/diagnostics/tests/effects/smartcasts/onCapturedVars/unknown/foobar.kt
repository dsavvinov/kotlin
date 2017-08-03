// !DIAGNOSTICS: -INVISIBLE_MEMBER -INVISIBLE_REFERENCE

import kotlin.internal.*

fun getBool(): Boolean = false

fun foo() {
    var x: Int?
    x = 5

    while(getBool()) {
        x = 5
    }

    x<!UNSAFE_CALL!>.<!>inc()
}