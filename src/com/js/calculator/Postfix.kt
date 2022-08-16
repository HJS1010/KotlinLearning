package com.js.calculator

import java.util.Stack

class Postfix {

    fun postfix(input: String): String {
        var res = mutableListOf<String>()
        val stack = Stack<String>()
        val list = input.split(' ')
        for (item in list) {
            val next = item.trim().trimStart()
            when {
                next == "(" -> stack.push(next)
                next == ")" -> handleParenthesis(stack, next, res)
                isDigital(next) -> res.add(next)
                stack.isEmpty() -> stack.push(next)
                isGreater(next, stack.peek()) -> stack.push(next)
                else -> handleLessOrEqual(stack, next, res)
            }
        }
        while (stack.isNotEmpty()) res.add(stack.pop())
        return res.joinToString(" ")
    }

    private fun handleParenthesis(stack: Stack<String>, next: String, res: MutableList<String>) {
        while (stack.isNotEmpty() && stack.peek() != "(") {
            res.add(stack.pop())
        }
        stack.pop()
    }

    private fun handleLessOrEqual(stack: Stack<String>, next: String, res: MutableList<String>) {
        while (stack.isNotEmpty() && stack.peek() != "(") {
            if (isLessOrEqual(next, stack.peek())) {
                res.add(stack.pop())
            }
        }
        stack.push(next)
    }

    private val operatorMap = mapOf(
        "+" to 1,
        "-" to 1,
        "*" to 2,
        "/" to 2
    )

    private fun isGreater(next: String, peek: String): Boolean =
        if (peek == "(") true
        else operatorMap[next]!! - operatorMap[peek]!! > 0


    private fun isLessOrEqual(next: String, peek: String) =
        operatorMap[next]!! - operatorMap[peek]!! <= 0

    private fun isDigital(s: String) = """\d+""".toRegex().matches(s)


}