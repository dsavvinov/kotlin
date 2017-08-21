// !LANGUAGE: +CalledInPlaceEffect

import kotlin.effects.dsl.*

inline fun <T, R> T.myLet(block: (T) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(this)
}


fun nonLocalReturnWithElvis(x: Int?): Int? {
    x?.myLet { return 42 }
    return x?.inc()
}