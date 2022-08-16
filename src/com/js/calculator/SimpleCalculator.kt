package com.js.calculator

class SimpleCalculator : ICalculator {

    override fun calculate(input: String): Int {
        val list = input.split(' ').filter { it.isNotBlank() }.map { it.trimStart() }
        if (list.size == 1) return list[0].toInt()
        return calculate(list)
    }

    private fun isDigital(s: String): Boolean {
        val regex = Regex("^([-+])?\\d+\$")
        return regex.matches(s)
    }

    private val plus = "+"
    private val minus = "-"
    private fun calculate(list: List<String>): Int {
        var sum = 0
        var operator = plus
        for (s in list) {
            when {
                isDigital(s) -> when (operator) {
                    plus -> sum += s.toInt()
                    minus -> sum -= s.toInt()
                }

                s.startsWith(plus) -> operator = plus
                s.startsWith(minus) -> operator = if (s.length % 2 == 0) plus else minus
                else -> {
                    when(operator){
                        plus -> sum += variablesMap[s]!!.toInt()
                        minus -> sum -= variablesMap[s]!!.toInt()
                    }
                }
            }
        }
        return sum
    }

}