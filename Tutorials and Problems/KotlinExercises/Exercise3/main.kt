// MemberReferences/MemberReferencesEx1.kt
data class Pet( .... )
  override fun toString() ....
}

enum class Habitat {
  LAND, WATER, AMPHIBIOUS;
  fun livesIn(pet: Pet) = pet.habitat == this
}

fun List<Pet>.liveOnLand(): List<Pet>  ....

fun List<Pet>.liveInWater(): List<Pet>  ....

fun List<Pet>.areAmphibious(): List<Pet> ....

fun List<Pet>.partitionAmphibious(): Pair<  .... >  ....

fun main() {
  val pets = listOf(
    Pet("Dog", LAND),
    Pet("Cat", LAND),
    Pet("Goldfish", WATER),
    Pet("Turtle", AMPHIBIOUS),
    Pet("Frog", AMPHIBIOUS)
  )



}