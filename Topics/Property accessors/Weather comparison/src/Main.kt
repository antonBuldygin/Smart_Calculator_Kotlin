class City(val name: String) {
    var degrees: Int = -5
        set(value) {
            field = if (value < -92 || value > 57) {
                when (name) {
                    "Moscow" -> 5
                    "Dubai" -> 30
                    "Hanoi" -> 20

                    else -> value
                }
            } else value

        }
    val defaultAge = -5
}

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()
    val firstCity = City("Dubai")
    firstCity.degrees = first
    val secondCity = City("Moscow")
    secondCity.degrees = second
    val thirdCity = City("Hanoi")
    thirdCity.degrees = third

    //implement comparing here
    when {
        firstCity.degrees < secondCity.degrees
                && firstCity.degrees < thirdCity.degrees-> println(firstCity.name)
        firstCity.degrees > secondCity.degrees
                && secondCity.degrees  < thirdCity.degrees-> println(secondCity.name)
        firstCity.degrees > thirdCity.degrees
                && secondCity.degrees > thirdCity.degrees-> println(thirdCity.name)
        else -> println("neither")

    }

}