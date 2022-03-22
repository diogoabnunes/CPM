fun createCounter(): Pair<()->Unit, ()->Int> {
  var counter = 13
  fun inc() {
    counter++
  }
  fun value(): Int {
    return counter
  }
  return Pair(::inc, ::value)
}

fun main() {
  val (inc, value) = createCounter()      // deconstruct a Pair

  for (k in 1..10)
    inc()
  println(value())
}

/* Also valid:

fun inc() = counter++
fun value() = counter

*/