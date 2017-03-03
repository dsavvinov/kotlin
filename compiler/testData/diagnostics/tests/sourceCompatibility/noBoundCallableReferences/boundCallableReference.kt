// !LANGUAGE: -BoundCallableReferences

class C { companion object }
val ok1 = C::hashCode
val fail1 = <!UNSUPPORTED_FEATURE!>C.Companion<!>::hashCode

object O {
    class Y {
        companion object
    }
}
val fail2 = <!UNSUPPORTED_FEATURE!>O<!>::hashCode
val ok2 = O::Y

fun hashCode() {}

val fail3 = <!UNSUPPORTED_FEATURE!>""<!>::hashCode
val fail4 = <!UNSUPPORTED_FEATURE!>(C)<!>::hashCode
val fail5 = <!UNSUPPORTED_FEATURE!>(C.Companion)<!>::hashCode
