class Robot( ... ) {
  .....
  
  fun crossBoundary( ... ): ... {
     ...
  }

  fun goRight(steps: Int) {
    .....
    
    x = crossBoundary(x)
  }

  ...

  fun getLocation(): String ....
  
  ....
 
}

fun main() {
  ...
}