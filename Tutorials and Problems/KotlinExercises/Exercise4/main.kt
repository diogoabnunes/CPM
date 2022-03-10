val Int.isEven: Boolean
  get() = .....

// Returns all the divisors of the number
// except 1 and the number itself
private fun getNonTrivialDivisors(num: Int): List<Int> {
  return (2 until num).filter { num % it == 0 }
}

fun Int.isPrime(): Boolean {
                                  // A List has an isEmpty() method
}

fun isPerfect(num: Int): Boolean {
                                  // A List has sum() method
}

fun main() {
  val range = 1..1000
  val even = range.filter(Int::isEven)

  // print the number of elements of even (it should be 500)

  val prime =  .... // create a list of primes from range
  
  // see if the first 7 are listOf(2, 3, 5, 7, 11, 13, 17)
  // see if the last 7 are listOf(953, 967, 971, 977, 983, 991, 997)

  val perfect =  .... // create a list of perfect numbers from range
  
  // see if it is listOf(6, 28, 496)
}