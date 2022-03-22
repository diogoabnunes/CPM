class Robot(x: Int, y: Int, fieldSize: Int) {
  var x = x 
  var y = y
  var fieldSize = fieldSize 

  fun goLeft(steps: Int) {
    x -= steps
    x %= fieldSize
  }

  fun goRight(steps: Int) {
    x += steps
    x %= fieldSize
  }

  fun goUp(steps: Int) {
    y += steps
    y %= fieldSize
  }

  fun goDown(steps: Int) {
    y -= steps
    y %= fieldSize
  }

  fun getLocation(): String {
    return x.toString() + ", " + y.toString()
  }

  override fun toString() = "Robot(x=" + x + ", y=" + y + ")"
}

fun main() {
  var robot = Robot(2, 3, 10)
  
  robot.goRight(10)
  robot.goUp(10)
  println(robot)
}