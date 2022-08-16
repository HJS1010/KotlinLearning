package com.js.calculator

import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import kotlin.test.assertEquals

class PostfixCalcTest {

    private lateinit var calc: PostfixCalc

    @BeforeTest
    fun setUp() {
        calc = PostfixCalc()
    }

    @Test
    fun postfixCalcTest() {
        assertEquals(23, calc.postfixCalc("10 2 8 * + 3 -"))
        assertEquals(15, calc.postfixCalc("2 3 4 + * 1 +"))
        assertEquals(21, calc.postfixCalc("2 3 4 2 * + 1 - * 1 +"))
        assertEquals(121, calc.postfixCalc("3 8 4 3 + 2 * 1 + * + 6 2 1 + / -"))
    }
}