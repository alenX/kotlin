package templates

import templates.Family.*

fun numeric(): List<GenericFunction> {
    val templates = arrayListOf<GenericFunction>()

    templates add f("sum()") {
        doc { "Returns the sum of all elements in the collection" }
        returns("SUM")
        body {
            """
            val iterator = iterator()
            var sum: SUM = ZERO
            while (iterator.hasNext()) {
                sum += iterator.next()
            }
            return sum
            """
        }
    }

    templates add f("average()") {
        doc { "Returns the average of all elements in the collection, or null if there are no elements" }
        returns("SUM?")
        body {
            """
            val iterator = iterator()
            var sum: SUM = ZERO
            var count = 0
            while (iterator.hasNext()) {
                count++
                sum += iterator.next()
            }
            return if (count == 0) return null else sum/count
            """
        }
    }

    return templates
}
