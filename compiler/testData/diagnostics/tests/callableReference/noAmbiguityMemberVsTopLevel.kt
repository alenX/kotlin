fun foo() {}

class A {
    fun foo() {}
    
    fun main() {
        val x = ::foo

        x : KMemberFunction0<A, Unit>
    }
}
