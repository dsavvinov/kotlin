/**
 * Current outcome: ERROR_TYPE on 'Data'. Frontend doesn't report error, but backend fails with
 * an exception.
 *
 * !! Supertypes of Base =>
 * Scope for supertypes of Base =>
 * Static scope of C =>
 * CO's hierarchy =>
 * Resolve DerivedAbstract succesfully => // because we provide special scope for CO header which doesn't sees locals
 * Start to resolve DerivedAbstract supertypes =>
 * Resolve Base successfully => // because supertypes of DerivedAbstract live in fileRoot-scope (note the difference with ISE)
 * !! Start to resolve Base supertypes =>
 * kaboom, recursion, create error type.
 *
 * Therefore, we have a ERROR_TYPE, and corresponding scope in hierarchy.
 * That means that we can't resolve Data, because we step on that scope.
 */
abstract class DerivedAbstract : C.Base()

class Data

open class C {
    open class Base {
        fun triggerErrorScope() {}
    }

    protected val _internal: <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>Data<!> = Data()

    companion object : DerivedAbstract()
}