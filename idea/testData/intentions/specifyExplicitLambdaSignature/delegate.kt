// IS_APPLICABLE: true
// SKIP_ERRORS_BEFORE
// ERROR: Modifier 'override' is not applicable to 'local function'
// ERROR: Type mismatch: inferred type is () -> Unit but Base was expected

// See also KT-15075

interface Base {
    fun foo()
}

class Derived : Base by <caret>{
    override fun foo() {}
}
