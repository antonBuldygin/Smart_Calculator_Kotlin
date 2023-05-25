package calculator

import java.util.*


class PostfixNotation {

    fun precedence(x: Char): Int {
        if (x == '^') {   // highest precedence
            return 2
        } else if (x == '*' || x == '/') {
            return 1 // second highest precedence
        } else if (x == '+' || x == '-') {
            // lowest precedence
            return 0
        }
        return -1 // not an operator
    }

    fun InfixToPostfix(str: String): String? {
        val stk = Stack<Char>() // used for converting infix to postfix
        var ans: String? = "" // string containing our final answer
        val n = str.length
        for (i in 0 until n) {
            val x = str[i]
            if (x >= '0' && x <= '9'||x==',') {
                ans += x
            } else if (x == '(') {     // push directly in the stack
                stk.push('(')
            } else if (x == ')') {
                while (!stk.isEmpty() && stk.peek() != '(') {          // keep popping till opening bracket is found
                    ans += ","+stk.pop()
                }
                if (!stk.isEmpty()) {
                    stk.pop()
                }
            } else {
                while (!stk.isEmpty() && precedence(stk.peek()) >= precedence(x)) {    // remove all higher precedence values
                    ans += stk.pop()+","
                }
                stk.push(x)
            }
        }
        while (!stk.isEmpty()) {
            ans += ","+stk.pop()
        }
        return ans
    }

}