fun foo(a: Any?) {
    <!SYNTAX!><!>@{ () : Int ->
        return<!SYNTAX!><!>@ 111
    }

    <!SYNTAX!><!>@ while(a == null) {
        if (true) {
            break<!SYNTAX!><!>@
        }
        else {
            continue<!SYNTAX!><!>@
        }
    }

    var <!ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE!>b<!> = 1

    (<!SYNTAX!><!>@ b) = <!UNUSED_VALUE!>2<!>

    return<!SYNTAX!><!>@ 1
}

open class A {
    fun foo() {}
}

class B : A() {
    fun bar() {
        super<!SYNTAX!><!>@.foo()
    }
}