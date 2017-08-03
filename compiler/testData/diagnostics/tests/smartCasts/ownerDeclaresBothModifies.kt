// !DIAGNOSTICS: -UNUSED_PARAMETER
fun foo(arg: Int?) {
    var x = arg
    if (x == null) return
    run {
        // now safe because we know that run's lambda executed here and once
        <!DEBUG_INFO_SMARTCAST!>x<!>.hashCode()
        x = null
    }
    // we know that we get data flow from run's lambda here
    if (<!SENSELESS_COMPARISON!><!DEBUG_INFO_CONSTANT!>x<!> != null<!>) x = 42
    // Unsafe because we know that previous if true branch never executed
    x<!UNSAFE_CALL!>.<!>hashCode()
}