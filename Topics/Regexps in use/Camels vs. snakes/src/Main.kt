fun getCamelStyleString(inputString: String): String {
    // put your code here

    val regex = """\_\w{1}""".toRegex()
    val matchres = regex.findAll(inputString)
    var result = inputString

    for (mt in matchres) {
//        result = inputString
        val value = mt.value
        val substring = mt.value.substring(1)
        result = result.replace(value, substring.uppercase())
    }
    return result.capitalize()
}

//fun main() {
//    val s = "a_modern_programming_language_that_makes_developers_happier"
//    println(getCamelStyleString(s))
//}