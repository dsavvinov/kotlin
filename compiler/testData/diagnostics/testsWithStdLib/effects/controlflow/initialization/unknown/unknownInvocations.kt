// !LANGUAGE: +CalledInPlaceEffect

import kotlin.effects.dsl.*

fun <T> inPlace(block: () -> T): T {
    contract {
        callsInPlace(block)
    }
    return block()
}

fun reassignmentAndNoInitializaiton() {
    val x: Int
    inPlace { <!VAL_REASSIGNMENT!>x<!> = 42 }
    <!UNINITIALIZED_VARIABLE!>x<!>.inc()
}