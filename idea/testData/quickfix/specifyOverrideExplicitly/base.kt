// "Specify override for 'foo' explicitly" "true"

interface A {
    fun foo()
}

open class B : A {
    override fun foo() {}
}

class C<caret>(a: A) : B(), A by a