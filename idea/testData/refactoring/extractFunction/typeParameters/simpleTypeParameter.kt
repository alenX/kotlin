open class Data(val x: Int)

class Pair<A, B>(val a: A, val b: B)

// NEXT_SIBLING:
fun foo<V: Data>(v: V): Pair<Int, V> {
    return <selection>Pair(v.x + 10, v)</selection>
}