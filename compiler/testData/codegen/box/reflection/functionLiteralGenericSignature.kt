import java.util.Date

fun assertGenericSuper(expected: String, function: Any?) {
    val clazz = (function as java.lang.Object).getClass()!!
    val genericSuper = clazz.getGenericSuperclass()!!
    if ("$genericSuper" != expected)
        throw AssertionError("Fail, expected: $expected, actual: $genericSuper")
}


val unitFun = { }
val intFun = { 42 }
val stringParamFun = { (x: String) : Unit -> }
val listFun = { (l: List<String>) : List<String> -> l }
val mutableListFun = { (l: MutableList<Double>) : MutableList<Int> -> null!! }
val funWithIn = { (x: Comparable<String>) : Unit -> }

val extensionFun = { Any.() : Unit -> }
val extensionWithArgFun = { Long.(x: Any) : Date -> Date() }

fun box(): String {
    assertGenericSuper("kotlin.jvm.internal.FunctionImpl<kotlin.Unit>", unitFun)
    assertGenericSuper("kotlin.jvm.internal.FunctionImpl<java.lang.Integer>", intFun)
    assertGenericSuper("kotlin.jvm.internal.FunctionImpl<kotlin.Unit>", stringParamFun)
    assertGenericSuper("kotlin.jvm.internal.FunctionImpl<java.util.List<? extends java.lang.String>>", listFun)
    assertGenericSuper("kotlin.jvm.internal.FunctionImpl<java.util.List<java.lang.Integer>>", mutableListFun)
    assertGenericSuper("kotlin.jvm.internal.FunctionImpl<kotlin.Unit>", funWithIn)

    assertGenericSuper("kotlin.jvm.internal.ExtensionFunctionImpl<java.lang.Object, kotlin.Unit>", extensionFun)
    assertGenericSuper("kotlin.jvm.internal.ExtensionFunctionImpl<java.lang.Long, java.util.Date>", extensionWithArgFun)

    return "OK"
}
