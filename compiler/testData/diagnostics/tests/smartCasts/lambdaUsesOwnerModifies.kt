// !DIAGNOSTICS: -UNUSED_PARAMETER
fun foo(arg: Int?) {
    var x = arg
    if (x == null) return
    run {
        // Now safe because we know that run executed exactly once in-place
        <!DEBUG_INFO_SMARTCAST!>x<!>.hashCode()
    }
    x = null  
}