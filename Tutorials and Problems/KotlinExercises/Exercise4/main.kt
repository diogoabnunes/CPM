val Int.isEven: Boolean
  get() = this % 2 == 0

// Returns all the divisors of the number
// except 1 and the number itself
private fun getNonTrivialDivisors(num: Int): List<Int> {
  return (2 until num).filter { num % it == 0 }
}

fun Int.isPrime(): Boolean {
  if (this == 1) return false
  return getNonTrivialDivisors(this).isEmpty()  // A List has an isEmpty() method
}

fun isPerfect(num: Int): Boolean {
  if (num == 1) return false
  return getNonTrivialDivisors(num).sum() + 1 == num  // A List has sum() method
}

fun main() {
  val range = 1..1000
  val even = range.filter(Int::isEven)

  // print the number of elements of even (it should be 500)
  println("Number of elements of even (it should be 500): " + even.size)

  val prime = range.filter(Int::isPrime) // create a list of primes from range
  
  // see if the first 7 are listOf(2, 3, 5, 7, 11, 13, 17)
  println("First 7 primes (should be [2, 3, 5, 7, 11, 13, 17]): " + prime.subList(0, 7))
  // see if the last 7 are listOf(953, 967, 971, 977, 983, 991, 997)
  println("Last 7 primes (should be [953, 967, 971, 977, 983, 991, 997]): " + prime.subList(prime.size - 7, prime.size))

  val perfect = range.filter(::isPerfect) // create a list of perfect numbers from range
  
  // see if it is listOf(6, 28, 496)
  println("Perfect numbers (should be [6, 28, 496]): " + perfect)
}