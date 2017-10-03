/**
 * Current outcome: code compiles and works fine, but there are hidden issues
 *
 * This is quite similar to ErrorType, but we don't have explicit type on field,
 * so, while resolving type of that property, we have to resolve its initializer's
 * type first. Eventually, this will end up in NROI, which doesn't give a heck about
 * ERROR_SCOPE and will happilly skip problematic scope, getting up to root scope,
 * where 'Data' will be successfully resolved.
 *
 * While it works, the issue is the same as with "NoErrorTypeBecauseNeededTypeIsLowEnough":
 * error scope is still here and can blow up unpredictably.
 */

abstract class DerivedAbstract : C.Base()

class Data

open class C {
    open class Base {
        open fun m() {}
    }

    val field = Data()

    companion object : DerivedAbstract() {
        override fun m() {}
    }
}