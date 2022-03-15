data class Pet(val name: String, val habitat: Habitat) {
  override fun toString() = name + " (" + habitat + ")" 
}

enum class Habitat {
  LAND, WATER, AMPHIBIOUS;
  fun livesIn(pet: Pet) = pet.habitat == this
}

fun List<Pet>.liveOnLand(): List<Pet>  {
  val landPets = this.filter { it.habitat == Habitat.LAND }
  return landPets
}

fun List<Pet>.liveInWater(): List<Pet> {
  val waterPets = this.filter { it.habitat == Habitat.WATER }
  return waterPets
}

fun List<Pet>.areAmphibious(): List<Pet> {
  val amphibiousPets = this.filter { it.habitat == Habitat.AMPHIBIOUS }
  return amphibiousPets
}

fun List<Pet>.partitionAmphibious(): Pair<List<Pet>, List<Pet>>  {
  val part = this.partition { it.habitat == Habitat.AMPHIBIOUS }
  return part
}

fun main() {
  val pets = listOf(
    Pet("Dog", Habitat.LAND),
    Pet("Cat", Habitat.LAND),
    Pet("Goldfish", Habitat.WATER),
    Pet("Turtle", Habitat.AMPHIBIOUS),
    Pet("Frog", Habitat.AMPHIBIOUS)
  )

  println("Live on land: " + pets.liveOnLand())
  println("Live in water: " + pets.liveInWater())
  println("Are amphibious: " + pets.areAmphibious())
  println("Partition Amphibious: " + pets.partitionAmphibious())
}