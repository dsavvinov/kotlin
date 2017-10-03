/**
 * Current outcome: everything works OK
 *
 * This test is here just to indicate one of possible proper use cases
 */

open class Base {
    open fun m() {}
}
abstract class DerivedAbstract : Base()

class Data

open class C {

    protected val _internal: Data = Data()

    companion object : DerivedAbstract() {
        override fun m() {}
    }
}