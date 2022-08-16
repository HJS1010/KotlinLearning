package com.js.calculator

import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import kotlin.test.assertEquals

class PostfixTest {

    private lateinit var fix: Postfix

    @BeforeTest
    fun setUp() {
        fix = Postfix()
    }

    @Test
    fun postfixTest() {
        assertEquals("10 2 8 * + 3 -", fix.postfix("10 + 2 * 8 - 3"))
        assertEquals("2 3 4 + * 1 +", fix.postfix("2 * ( 3 + 4 ) + 1"))
        assertEquals("2 3 4 2 * + 1 - * 1 +", fix.postfix("2 * ( 3 + 4 * 2 - 1 ) + 1"))
        assertEquals("3 8 4 3 + 2 * 1 + * + 6 2 1 + / -", fix.postfix("3 + 8 * ( ( 4 + 3 ) * 2 + 1 ) - 6 / ( 2 + 1 )"))
    }
}