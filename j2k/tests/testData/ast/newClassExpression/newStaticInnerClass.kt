package demo

open class Foo() {
    class object {
        open class Bar() {
        }
    }
}

open class User() {
    open fun main() {
        var boo: Foo.Bar? = Foo.Bar()
    }
}