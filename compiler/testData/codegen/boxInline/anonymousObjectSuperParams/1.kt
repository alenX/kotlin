import test.*

fun box() : String {
    val o = "O"
    val k = "K"
    val result = doWork {
        val s = object : A<String>("11") {
            override fun getO(): String {
                return o;
            }

            override fun getK(): String {
                return k;
            }
        }

        s.getO() + s.getK() + s.param
    }

    if (result != "OK11") return "fail $result"

    return "OK"
}

