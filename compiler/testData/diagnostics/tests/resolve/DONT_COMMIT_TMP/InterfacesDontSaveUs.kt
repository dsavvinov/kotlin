/**
 * Current outcome: frontend fails with ISE
 *
 * This case is very similar to ISE.kt, but instead of classes we use interfaces.
 * So, in fact, we have NO cyclis scopes here, but implementation still fails to
 * process this case properly.
 */

open class C {
    interface Base {
        open fun m() {}
    }

    interface DerivedAbstract : Base

    companion object : DerivedAbstract {
        override fun m() {}
    }
}