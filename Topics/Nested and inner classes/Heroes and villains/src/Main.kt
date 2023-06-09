class Hero {
    val baseStrength = 1000
    class Equipment {
        val weapon = "trident"
        val weaponStrength = 300
    }
}

class Villain {
    val baseStrength = 500
    class Equipment {
        val weapon = "bomb"
        val weaponStrength = 700
    }
}

fun main() {

    val heroEquipment = Hero.Equipment()
    val heroTotalStrength = heroEquipment.weaponStrength+Hero().baseStrength

    val villainEquipment = Villain.Equipment()
    val villainTotalStrength = villainEquipment.weaponStrength+Villain().baseStrength

    // the following lines should remain as they are
    println("The hero uses ${heroEquipment.weapon}. The total strength is $heroTotalStrength.")
    println("The villain uses ${villainEquipment.weapon}. The total strength is $villainTotalStrength.")
}