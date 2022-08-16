package com.js.calculator

fun main() {
    Calculator().calculate()
}

class Calculator {
    private val exitMsg = "Bye!"
    private val helpMsg = "The program calculates the addition/subtraction of numbers"
    private val unknownMsg = "Unknown command"
    private val unknownVariableMsg = "Unknown variable"
    private val invalidInputMsg = "Invalid expression"
    private val invalidIdentifierMsg = "Invalid identifier"
    private val invalidAssignmentMsg = "Invalid assignment"

    fun calculate() {
        while (true) {
            var canVariableCalculate = false
            var canCalculate = false
            val input = readLine()!!.trim()
            if (input.isBlank()) continue
            when (Checker(input).check()) {
                Status.EXIT -> println(exitMsg).also { return }
                Status.HELP -> println(helpMsg)
                Status.INVALID_COMMAND -> println(unknownMsg)
                Status.INVALID_INPUT -> println(invalidInputMsg)
                Status.INVALID_VARIABLES -> println(invalidIdentifierMsg)
                Status.INVALID_ASSIGNMENT -> println(invalidAssignmentMsg)
                Status.VARIABLES_DECLARED -> canVariableCalculate = true
                Status.VARIABLE -> handleVariable(input)
                Status.OK -> canCalculate = true
            }
            if (canCalculate) simpleCalc(input)
            if (canVariableCalculate) variablesCalc(input)

        }
    }

    private fun handleVariable(input: String) {
        if (variablesMap.containsKey(input)) println(variablesMap[input])
        else println(unknownVariableMsg)
    }

    private fun simpleCalc(input: String) {
        SimpleCalculator().calculate(input).also { println(it) }
    }

    private fun variablesCalc(input: String) {
        VariablesCalculator().calculate(input)
    }

}


