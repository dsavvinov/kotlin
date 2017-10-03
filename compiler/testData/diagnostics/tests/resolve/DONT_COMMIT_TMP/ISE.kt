/**
 * Current outcome: frontend fails with ISE.
 *
 * Supertypes of Base =>
 * Scope for supertypes of Base =>
 * !! Static scope of C =>
 * CO's hierarchy =>
 * Resolve DerivedAbstract succesfully => // because we provide special scope for CO header which doesn't sees locals
 * Start to resolve DerivedAbstract supertypes =>
 * !! Static scope of C =>   // we create ThrowingLexicalScope here and start to pass it around
 * Resolve type of Base in scope=ThrowingLexicalScope =>
 * it will throw, eventually
 */
open class C {
    open class Base {
        open fun m() {}
    }

    abstract class DerivedAbstract : Base()

    companion object : DerivedAbstract() {
        override fun m() {}
    }
}