// !DIAGNOSTICS: -INVISIBLE_MEMBER -INVISIBLE_REFERENCE

import kotlin.internal.*

fun <T> myRun(@CalledInPlace(InvocationCount.EXACTLY_ONCE) block: () -> T): T = block()

fun foo() {
    var x: Int?

    x = 42

    myRun { x = null }

    <!DEBUG_INFO_CONSTANT!>x<!><!UNSAFE_CALL!>.<!>inc()
}