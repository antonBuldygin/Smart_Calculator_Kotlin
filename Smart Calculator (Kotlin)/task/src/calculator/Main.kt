package calculator

import java.math.BigInteger

class Main

fun main() {
    var b = true
    val rexHelp = "[/][\\w]+".toRegex()
    val rexOneDigit = "[-+]*[\\d]+".toRegex()
    val rexinv = "([\\d]+[-+]+)|[\\d\\s]+".toRegex()
    val rexinvSim = "\\s*[a-zA-Z]+\\s*".toRegex()
    var mapWithVar = mutableMapOf<String, BigInteger>()
    val toRegex = "[\\s]*[a-z A-Z]+[\\s]*[=][\\s]*[-\\w]+[\\s]*".toRegex()

    val digit = "[-\\d]+".toRegex()
    val letter = "\\s*[a-z A-Z]+\\s*".toRegex()
    var extracted: String = ""
    val postfix = PostfixNotation()
    var infixToPostfix: String?


    while (b) {
        val readln = readln().toString()
        val input = readln.split(Regex("\\s+"))

//        if(rexHelp.matches(readln)){
//            println("help")}


        if (input.size == 1 && input[0] == "") continue
        else if (rexHelp.matches(readln)) {
            when {

                input.size == 0 -> break
                readln.contains("/exit") -> {
                    println("Bye!")
                    b = false
                    return
                }

                readln.contains("/help") -> {
                    println("The program calculates the sum of numbers")

                }

                readln.contains("/") && !input.contains("/help") && !input.contains("/exit") -> {
                    println("Unknown command")

                }

                input.size == 1 -> println("${input[0]}")

            }

        } else {
            if (rexOneDigit.matches(readln)) {

                if (input[0].contains("+")) {
                    println("${input[0].substring(1)}")
                } else println("${input[0]}")
                continue
            }

            if (toRegex.matches(readln)) {
                val replace = readln.replace("\\s+".toRegex(), "")
//                println(replace)
                val split = replace.split("=")
                var key = split[0].trim()
                var value = split[1]
                if (digit.matches(value)) {
                    mapWithVar[key] = split[1].toBigInteger()
                } else if (!digit.matches(value) && mapWithVar[value] != null) {
                    var count = 0
                    for ((k, v) in mapWithVar) {
                        if (k == value) {
                            count++
                            mapWithVar[key] = v
                        }
                    }
                    if (count == 0) {
                        println("Unknown variable")
                    }

                } else if (!digit.matches(value) && mapWithVar[value] == null) {
                    println("Invalid assignment")

                } else if (!letter.matches(value)) {
                    println("Invalid assignment")
                }
                continue
            } else if (input.contains("=")) {
                println("Invalid identifier")
                continue
//            println(mapWithVar.toString())
            }

            if (rexinv.matches(readln)) {
                println("Invalid expression")
                continue
            }

            if (rexinvSim.matches(readln) && !mapWithVar.contains(readln.trim())) {
                println("Unknown variable")
                continue
            }

            if (rexinvSim.matches(readln) && mapWithVar.contains(readln.trim())) {
                println(mapWithVar[readln.trim()])
                continue
            }

//        if (readln.contains(calculRegex)) {
            var readln1 = readln
            for ((k, v) in mapWithVar) {

                if (readln.contains(k)) {
                    readln1 = readln1.replace(k, v.toString())
                }
            }

            val input1 = readln1.split(Regex("\\s+"))

            if (readln1.contains("(") || readln1.contains(")")) {
                val parantheses =
                    "(.*?[+-/*]{0,1}\\(\\s*\\-?\\s*[\\w]+\\s*(\\s*[+\\*\\-/]+\\s*[\\w]+\\s*){0,3}\\).*?)".toRegex()
//                println(parantheses.matches(readln1))
                if (parantheses.matches(readln1)) {
                    extracted = extracted(readln1)


                    4
                } else {
                    println("Invalid expression")
                    continue
                }
            }
//            } else println("CLEARED")
            if (!readln1.contains("(") && !readln1.contains(")")) {
                extracted = extracted(readln1)
            }

            if (extracted == "3377733") {
                continue
            }
            infixToPostfix = postfix.InfixToPostfix(extracted)
            var split = infixToPostfix?.split(",")?.toMutableList()
//            println(split)
            val size = split?.size

            if (size != null) {
                for (s in 0..size - 1) {
                    if (split?.get(s) == "") {
                        split[s] = "0"
                    }
                }
            }


            val calculator = Calculator()
            println(calculator.calcul(split))
//            extracted(input1, mapWithVar)
//        }
        }
    }

}

private fun extracted(input: List<String>, map: Map<String, Int>) {


    var s = 0
    var minusCount = 0
    for (i in 0..input.size - 1) {
//        if (!input[i].contains(Regex("\\d+"))) {
////            println("нет Цифры")
//        }
        if (input[i].contains("+")) {
            continue
        }
        if (input[i].contains("-") && input[i].contains(Regex("\\d+"))) {

            for (k in 0..input[i].length - 1) {

                val c = input[i][k]
                if (c.equals('-')) {
                    minusCount++
                }
                if (c in '0'..'9') {
                    if (minusCount % 2 == 0) {
                        s += c.toString().toInt()
                    } else s -= c.toString().toInt()
                    minusCount = 0
                }

            }

        }

        if (input[i].contains("-") && !input[i].contains(Regex("\\d+"))) {
            if (input[i].length > 1) {
                for (k in 0..input[i].length - 1) {
                    if (input[i][k].toChar().equals('-')) {
                        minusCount++
                    }

                }
            } else minusCount++
        }

        if (!input[i].contains("-") && minusCount % 2 == 0 && input[i].contains(Regex("\\d+"))) {
            s += input[i].toInt()
        }
        if (!input[i].contains("-") && minusCount % 2 != 0) {
            s -= input[i].toInt()
            minusCount = 0
        }
    }

//    println(s)
}

private fun extracted(read: String): String {
    val digit = "[\\d]||[(]||[)]".toRegex()
    var minusCount = 0
    var plusCount = 0
    var devideCount = 0
    var multiCount = 0
    var final = ""
    var digitsCount = 0

    for (i in 0..read.length - 1) {


        if (read[i] == ' ') {
            continue
        }
        if (read[i] == '-') {
            minusCount++
        }

        if (read[i] == '+') {
//            if (plusCount > 0) {
//                println("Invalid expression")
//                plusCount = 0
////                return "3377733"
//            } else {
            plusCount++
//                digitsCount = 0
//            }
        }

        if (read[i] == '*') {
            if (multiCount > 0) {
                println("Invalid expression")
                multiCount = 0
                return "3377733"
            } else {
                multiCount++
                digitsCount = 0
            }
        }
        if (read[i] == '/') {
            if (devideCount > 0) {
                println("Invalid expression")
                devideCount = 0
                return "3377733"
            } else {
                devideCount++
                digitsCount = 0
            }
        }
        if (digit.matches(read[i].toString())) {
            if (minusCount == 0 && plusCount == 0 && devideCount == 0 && multiCount == 0 && digitsCount != 0) {
                final = final + read[i].toString()
            }
            if (final.length == 0 && minusCount == 0 && plusCount == 0 && multiCount == 0 && devideCount == 0) {
                final = final + read[i]
                    .toString()
                digitsCount++
            }
            if (devideCount == 1) {
                final = final + ",/" + read[i].toString()
                digitsCount++
                devideCount = 0
            }
            if (multiCount == 1) {

                if (final.length == 0) {
                    final = read[i].toString()
                    digitsCount++
                    multiCount = 0
                } else {
                    final = final + ",*" + read[i].toString()
                    digitsCount++
                    multiCount = 0
                }
            }
            if (plusCount != 0 && minusCount == 0 && devideCount == 0 && multiCount == 0) {
                if (final.length == 0) {
                    final = read[i].toString()
                    digitsCount++
                    plusCount = 0
                } else {
                    final = final + ",+" + read[i].toString()
                    digitsCount++
                    plusCount = 0
                }
            }
            if (minusCount % 2 == 0 && minusCount != 0) {
                final = final + ",+" + read[i].toString()
                digitsCount++
                minusCount = 0
            }
            if (minusCount % 2 != 0) {
                final = final + ",-" + read[i].toString()
                digitsCount++
                minusCount = 0
            }

//                if (minusCount == 0 && plusCount == 0 && devideCount == 0 && multiCount == 0&&digitsCount!=0) {
//                    final = final +  read[i].toString()
//                }
//                else final = final + read[i].toString()

        }



        if (minusCount != 0 && (plusCount != 0 || devideCount != 0 || multiCount != 0)) {
//            println("erorr1")
            minusCount = 1
            plusCount = 0
            devideCount = 0
            multiCount = 0
        }

        if (plusCount != 0 && (minusCount != 0 || devideCount != 0 || multiCount != 0)) {
//            println("erorr2")
            minusCount = 1
            plusCount = 0
            devideCount = 0
            multiCount = 0
        }
        if (devideCount != 0 && (minusCount != 0 || plusCount != 0 || multiCount != 0)) {
//            println("erorr3")
            minusCount = 0
            plusCount = 0
            devideCount = 0
            multiCount = 0
        }
        if (multiCount != 0 && (minusCount != 0 || devideCount != 0 || plusCount != 0)) {
//            println("erorr4")
            minusCount = 0
            plusCount = 0
            devideCount = 0
            multiCount = 0
        }

    }

//        println(minusCount)
//        println(plusCount)
//        println(multiCount)
//        println(devideCount)
//        println(digitsCount)
//    println(final)
    return final
}
