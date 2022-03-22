fun createContainer(): Pair<(numuber:Int)->Unit, ()->Int?> {
    var container:Int? = null
    fun add(n:Int) {
        container = n
    }
    fun remove():Int? {
        var content = container 
        container = null
        return content
    }
    return Pair(::add, ::remove)
}

fun main() {
    val (add, remove) = createContainer() //deconstruct a Pair

    add(13)
    println(remove())
    println(remove())
}