class Fail2 {
    open class A {
        val triggerA: Int = 42
    }

    open class B : Container.FromContainer, A() {
//        val triggerB: Int = 42
    }

    open class C : B() {
//        val triggerC: Int = 42
    }

    open class Container {
        interface FromContainer

        val foo: String = "hello"

        companion object : C()
    }
}