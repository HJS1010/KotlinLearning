package com.js.calculator

val variablesMap = mutableMapOf<String, String>()

class VariablesCalculator : ICalculator {

    override fun calculate(input: String) {
        val (k, v) = split(input)
        try {
            variablesMap[k] = v.toInt().toString()
        } catch (e: NumberFormatException) {
            if (variablesMap.containsKey(v)) variablesMap[k] = variablesMap[v]!!
            else println("Unknown variable")
        }
    }

    fun split(input: String) = input.split('=')
        .filter { it.isNotBlank() }
        .map { it.trim() }
        .map { it.trimStart() }
}


fun main() {
    val (key, value) = VariablesCalculator().split("a = 3")
    println("key = $key, value = $value")
}


