fun createCounter(): Pair<  > {
  val counter = 0;
  fun inc() = counter++;
  fun value() = counter;
}

fun main() {
  val (inc, value) = createCounter()      // deconstruct a Pair

  for (i in 1..10) inc
  println(value)
}