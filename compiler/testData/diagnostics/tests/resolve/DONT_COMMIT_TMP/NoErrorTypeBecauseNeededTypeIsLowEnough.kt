/**
 * Current outcome: code compiles and works, but there are hidden errors
 *
 * Here, error scope like in "ErrorType.kt" and other shit is created, but *not reported*.
 * 'Data' is resolved succesfully, because its declaration is *low enough to not step
 * onto error scope*.
 *
 * This is important case, because it clearly shows that we can't forbid anything without
 * inventing proper deprecation cycle.
 */

abstract class DerivedAbstract : C.Base() {
    override abstract fun m()
}

public class C {
    class Data

    open class Base () {
        open fun m() {}
    }

    protected val _internal: Data = Data()

    companion object : DerivedAbstract() {
        override fun m() {}
    }
}