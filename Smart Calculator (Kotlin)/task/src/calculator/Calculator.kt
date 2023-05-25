package calculator

import java.math.BigInteger
import java.util.*

class Calculator {

    fun calcul(list: MutableList<String>?) :BigInteger? {
        val stk = Stack<String>() // used for converting infix to postfix
        var ans: Int? =null
        val  digit = "\\d+".toRegex()

        var a :BigInteger?
        var b :BigInteger?
        var c :BigInteger?

        if (list != null) {
            for (l in list){

                if(l.contains(digit)){
                    stk.push(l)

                }
                if(l.contains("-")){
                    a = stk.pop().toBigInteger()
                    b = stk.pop().toBigInteger()
                    c= b-a
                    stk.push(c.toString())
                }

                if(l.contains("*")){
                    a = stk.pop().toBigInteger()
                    b = stk.pop().toBigInteger()
                    c= a*b
                    stk.push(c.toString())
                }
                if(l.contains("/")){
                    a = stk.pop().toBigInteger()
                    b = stk.pop().toBigInteger()
                    c= b/a
                    stk.push(c.toString())
                }
                if(l.contains("+")){
                    a = stk.pop().toBigInteger()
                    b = stk.pop().toBigInteger()
                    c= a+b
                    stk.push(c.toString())
                }

            }
        }

        return stk.pop().toBigInteger()
    }
}