package test

import java.io.Serializable

public open class AllBoundsInWhen : Object() {
    public open fun <T> foo() where T: Serializable {
        throw UnsupportedOperationException()
    }
}
