class Floating {
  var d: Double = 0.0
  
  constructor(d: Double) {
    this.d = d
  }

  override fun toString() = "d: " + d.toString()
}

fun main() {
  val floating = Floating(13.0)
  println(floating)
}