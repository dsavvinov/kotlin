// "Specify override for 'foo' explicitly" "true"

interface A {
    fun foo()
}

open class B : A {
    override fun foo() {}
}

class C<caret>(val a: A) : B(), A by a