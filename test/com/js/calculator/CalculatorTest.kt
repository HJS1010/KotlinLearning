package com.js.calculator

import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class CalculatorTest {
    private val validInput1 = "+15"
    private val validInput2 = "-22"
    private val validInput3 = "8  +    7"
    private val validInput4 = "8  +    7    - 4"
    private val validInput5 = "18  +++  22"
    private val validInput6 = "18  ---     22"
    private val validInput7 = "18  +++     22 - 33"

    private lateinit var regex: Regex
    private lateinit var invalidIdentifierRegex: Regex
    private lateinit var simpleCalculator: SimpleCalculator

    @BeforeTest
    fun setUp() {
        regex = Regex(pattern)
        invalidIdentifierRegex = Regex(invalidIdentifierPattern)
        simpleCalculator = SimpleCalculator()
    }

    @Test
    fun regexTestTrue() {
        assert(regex.matches(validInput1))
        assert(regex.matches(validInput2))
        assert(regex.matches(validInput3))
        assert(regex.matches(validInput4))
        assert(regex.matches(validInput5))
        assert(regex.matches(validInput6))
        assert(regex.matches(validInput7))
    }

    @Test
    fun regexTestFalse() {
        assertFalse(regex.matches("abc"))
        assertFalse(regex.matches("15+"))
        assertFalse(regex.matches("15-"))
        assertFalse(regex.matches("-15-15"))
        assertFalse(regex.matches("+15+15"))
        assertFalse(regex.matches("-15+15"))
        assertFalse(regex.matches("+15-15"))
        assertFalse(regex.matches("3+15"))
        assertFalse(regex.matches("18+22+"))
        assertFalse(regex.matches("18    22"))
        assertFalse(regex.matches("18  +++     22 - 33+ 99"))
    }

    @Test
    fun calculateTest() {
        assertEquals(15, calculate(validInput1))
        assertEquals(-22, calculate(validInput2))
        assertEquals(15, calculate(validInput3))
        assertEquals(11, calculate(validInput4))
        assertEquals(40, calculate(validInput5))
        assertEquals(-4, calculate(validInput6))
        assertEquals(7, calculate(validInput7))
    }

    private fun calculate(input: String) = simpleCalculator.calculate(input)


    @Test
    fun test() {
        validInput3.split(' ')
            .filter { it.isNotBlank() }
            .map { it.trimStart() }
            .forEach(::println)
    }

    @Test
    fun testVariablesRegex() {
        assert(invalidIdentifierRegex.matches("a2"))
        assert(invalidIdentifierRegex.matches("a2a"))
        assert(invalidIdentifierRegex.matches("a22"))
        assert(invalidIdentifierRegex.matches("a1 = 8"))
        assert(invalidIdentifierRegex.matches("n1 = a2a"))
    }


}