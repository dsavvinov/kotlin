
fun runDoesntForbidSmartcast() {
    var x: Int? = null

    run { if (x != null) <!DEBUG_INFO_SMARTCAST!>x<!>.inc() else x = 42 }

    if (x != null) <!DEBUG_INFO_SMARTCAST!>x<!>.inc() else x = 42
}