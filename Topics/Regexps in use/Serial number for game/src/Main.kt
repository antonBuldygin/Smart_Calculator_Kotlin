fun findSerialNumberForGame(listGames: List<String>) {
val par = readln().toString()
    var split:List<String>

//    val regex = """[\\w]+@[\\w]+@[\\w]+""".toRegex()
//    val regexXBox = """@[\\w]+@""".toRegex()
//    val regexPc = """@[\\w]+""".toRegex()

    for (r in listGames){

        if(r.contains(par)) {
//            println(r)
            val split = r.split("@")
            println("Code for Xbox - ${split[1]}, for PC - ${split[2]}")
        break

        }


    }

}

//fun main(){
//    val t = listOf("re@43@43", "t@56@34")
//
//    findSerialNumberForGame(t)
//
//}