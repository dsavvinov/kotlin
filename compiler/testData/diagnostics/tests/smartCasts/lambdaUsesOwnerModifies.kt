// !DIAGNOSTICS: -UNUSED_PARAMETER
fun foo(arg: Int?) {
    var x = arg
    if (x == null) return
    run {
        // Now safe because we know that run calls this lambda in-place
        <!SMARTCAST_IMPOSSIBLE!>x<!>.hashCode()
    }
    x = null  
}