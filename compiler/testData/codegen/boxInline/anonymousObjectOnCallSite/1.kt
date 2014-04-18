import test.*

fun box() : String {
    val o = "O"
    val k = "K"
    return doWork {
        val s = object : A<String>() {
            override fun getO(): String {
                return o;
            }

            override fun getK(): String {
                return k;
            }
        }

        s.getO() + s.getK()
    }
}

