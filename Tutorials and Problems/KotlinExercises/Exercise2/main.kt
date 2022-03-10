fun createContainer(): Pair<  > {
    var container: Array<Int> = arrayOf()
    fun add() {

    }
    fun remove() {

    }
    return Pair(::add, ::remove)
}

fun main() {
    val (add, remove) = createContainer()

    add()

}