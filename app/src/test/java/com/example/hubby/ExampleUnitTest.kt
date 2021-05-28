package com.example.hubby

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

// RunWith - указывает Runner отвечающий за запуск тестов
@RunWith(JUnit4::class)
class ExampleUnitTest {
    // Test method !
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }




}

