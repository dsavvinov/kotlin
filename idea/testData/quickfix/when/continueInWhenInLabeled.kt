// "Add '@loop' to continue" "true"
// WITH_RUNTIME

fun foo(chars: CharArray) {
    val length = chars.size
    var pos = 0
    loop@ while (pos < length) {
        val c = chars[pos]
        when (c) {
            '\n' -> continue<caret>
        }
        pos++
    }
}