package com.js.calculator

import java.util.Stack

class PostfixCalc {

    fun postfixCalc(input: String): Int {
        val list = input.split(' ')
        val stack = Stack<Int>()
        for (item in list) {
            when {
                isDigital(item) -> stack.push(item.toInt())
                else -> handle(stack, item)
            }
        }
        return stack.peek()
    }

    private fun handle(stack: Stack<Int>, item: String) {
        val first = stack.pop()
        val second = stack.pop()
        when (item) {
            "*" -> stack.push(first * second)
            "/" -> stack.push(second / first)
            "+" -> stack.push(second + first)
            "-" -> stack.push(second - first)
        }
    }


    private fun isDigital(s: String) = """\d+""".toRegex().matches(s)
}