class Container {
    val y: Int = 42

    open class A
    open class B : A()
    open class C : B()

    companion object : X()
}

open class Z {
    class NestedInZ
}

open class X {
    companion object : Z()

    val x: Int = 42
}