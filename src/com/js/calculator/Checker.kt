package com.js.calculator

val pattern = """^[+-]?([a-zA-Z\d]+\s+[+-]+\s+)*[a-zA-Z\d]+""".trimIndent()
val variablePattern = """^[a-zA-Z]+\s*=\s*[a-zA-Z]+$""".trimIndent()
val variablePattern2 = """^[a-zA-Z]+\s*=\s*\d+$""".trimIndent()
val invalidIdentifierPattern = """^[a-zA-Z]+\d+[a-zA-Z]*\s*=*\s*[a-zA-Z]*\d*[a-zA-Z]*""".trimIndent()
val invalidAssignmentPattern = """^[a-zA-Z]+(\s*=\s*[a-zA-Z]*\d+[a-zA-Z]*)+""".trimIndent()

class Checker(private val input: String) {

    fun check(): Status =
        when {
            checkExit() -> Status.EXIT
            checkHelp() -> Status.HELP
            checkInvalidCommand() -> Status.INVALID_COMMAND
            checkVariableDeclared() -> Status.VARIABLES_DECLARED
            checkInvalidIdentifier() -> Status.INVALID_VARIABLES
            checkInvalidAssignment() -> Status.INVALID_ASSIGNMENT
            checkVariableValid() -> Status.VARIABLE
            checkValid() -> Status.OK
            else -> Status.INVALID_INPUT
        }

    private fun checkVariableValid() = """^[a-zA-Z]+""".toRegex().matches(input)

    private fun checkInvalidAssignment() = invalidAssignmentPattern.toRegex().matches(input)

    private fun checkInvalidIdentifier() = invalidIdentifierPattern.toRegex().matches(input)

    private fun checkVariableDeclared() = variablePattern.toRegex().matches(input)
            || variablePattern2.toRegex().matches(input)

    private fun checkValid() = pattern.toRegex().matches(input)

    private fun checkInvalidCommand() = input.startsWith("/")
            && input != "/exit" && input != "/help"

    private fun checkExit() = input == "/exit"

    private fun checkHelp() = input == "/help"


}