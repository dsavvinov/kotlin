fun foo() {
    class A {
        // it's not a statement, while it has a block expression as an ancestor
        val w = 1.try<caret>
    }
}
