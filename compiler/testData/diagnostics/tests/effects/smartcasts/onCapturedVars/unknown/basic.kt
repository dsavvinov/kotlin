// !DIAGNOSTICS: -INVISIBLE_MEMBER -INVISIBLE_REFERENCE

import kotlin.internal.*

fun runRandomly(@CalledInPlace block: () -> Unit) = block()

fun getInt(): Int? = null

fun getBool(): Boolean = false

//fun notChangingClosure() {
//    var x: Int?
//    x = 5
//
//    runRandomly {
//        // should be smartcasted as we know that this lambda called in-place
//        <!DEBUG_INFO_SMARTCAST!>x<!>.inc()
//    }
//
//    // should be smarcasted (works even without effects)
//    <!DEBUG_INFO_SMARTCAST!>x<!>.inc()
//}

fun changingClosurePotentiallyCorrect() {
    var x: Int?
    x = 5

    runRandomly {
        // potentially correct, but forbidden now
        <!SMARTCAST_IMPOSSIBLE!>x<!>.inc()
        x = 5
    }

    // potentially correct, but forbidden now
    <!SMARTCAST_IMPOSSIBLE!>x<!>.inc()
}

fun changingClosureIncorrectSmartcast() {
    var x: Int?
    x = 5

    runRandomly {
        // must be forbidden
        <!SMARTCAST_IMPOSSIBLE!>x<!>.inc()
        x = null
    }

    // must be forbidden
    <!SMARTCAST_IMPOSSIBLE!>x<!>.inc()
}

fun localFlowAlwaysNotNullOnExit() {
    var x: Int?
    x = 5

    runRandomly {
        x = 5
        // should be smartcasted as we know that this lambda called in-place + local flow in lambda body
        <!SMARTCAST_IMPOSSIBLE!>x<!>.inc()
    }

    // potentially correct, but forbidden now
    <!SMARTCAST_IMPOSSIBLE!>x<!>.inc()
}

fun localFlowAlwaysNullOnExit() {
    var x: Int?
    x = 5

    runRandomly {
        x = 5
        // should be smartcasted as we know that this lambda called in-place + local flow in lambda body
        <!SMARTCAST_IMPOSSIBLE!>x<!>.inc()
        x = null

        // should be forbidden as we know that x is null here
        <!DEBUG_INFO_CONSTANT!>x<!><!UNSAFE_CALL!>.<!>inc()
    }

    // should be forbidden
    x<!UNSAFE_CALL!>.<!>inc()
}

    // should be forbidden
    <!SMARTCAST_IMPOSSIBLE!>x<!>.inc()
}

fun getUnknownBool(): Boolean = false



fun localFlowUnknownAtExit() {
    var x: Int?
    x = 5

    runRandomly {
        if (x != null) {
            // should be smartcasted as we know that this lambda called in-place + local flow in lambda body
            <!DEBUG_INFO_SMARTCAST!>x<!>.inc()
        } else {
            x = 42
        }

        // should be smartcasted as we know that this lambda called in-place + local flow in lambda body
        <!DEBUG_INFO_SMARTCAST!>x<!>.inc()

        if (getUnknownBool()) x = 42 else x = null
    }

    // should be forbidden, as lambda's flow is unknown at exit
    x<!UNSAFE_CALL!>.<!>inc()
}