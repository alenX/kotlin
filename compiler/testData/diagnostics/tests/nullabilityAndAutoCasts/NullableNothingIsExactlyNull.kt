fun test() {
  val out : Int? = null
  val x : Nothing? = null
  if (out != x)
    <!DEBUG_INFO_AUTOCAST!>out<!>.plus(1)
  if (out == x) return
  <!DEBUG_INFO_AUTOCAST!>out<!>.plus(1)
}
