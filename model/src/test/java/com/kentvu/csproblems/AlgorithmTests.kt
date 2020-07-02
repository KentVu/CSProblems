package com.kentvu.csproblems

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class AlgorithmTests {
    val arr = arrayOf(1, 4, 3, 6, 7, 2)
    @Test
    fun insertionSortTest() {
        assertThat(Playground().insertionSortDec(arr), CoreMatchers.equalTo(10))
    }
    @Test
    fun insertionSortAscTest() {
        assertThat(Playground().insertionSortAsc(arr), CoreMatchers.equalTo(5L))
        assertThat(Playground().insertionSortAsc(arrayOf(1,1,1,2,2)), CoreMatchers.equalTo(0L))
        assertThat(Playground().insertionSortAsc(TestCases.tc1), CoreMatchers.equalTo(2402763267))
    }
}