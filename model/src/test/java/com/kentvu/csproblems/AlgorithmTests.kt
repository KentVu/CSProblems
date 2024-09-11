package com.kentvu.csproblems

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class AlgorithmTests {
    val arr = intArrayOf(1, 4, 3, 6, 7, 2)
    @Test
    fun insertionSortTest() {
        assertThat(ReflectPlayground().insertionSortDec(arr), CoreMatchers.equalTo(10))
    }
    @Test
    fun insertionSortAscTest() {
        assertThat(ReflectPlayground().insertionSortAsc(arr), CoreMatchers.equalTo(5L))
        assertThat(ReflectPlayground().insertionSortAsc(intArrayOf(1,1,1,2,2)), CoreMatchers.equalTo(0L))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc1), CoreMatchers.equalTo(2402763267))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc2.value), CoreMatchers.equalTo(2506500141))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc3), CoreMatchers.equalTo(2499877242))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc4), CoreMatchers.equalTo(2501897494))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc5), CoreMatchers.equalTo(2495566249))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc6), CoreMatchers.equalTo(2501269610))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc7), CoreMatchers.equalTo(2505761919))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc8), CoreMatchers.equalTo(2492093573))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc9), CoreMatchers.equalTo(2490880710))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc10), CoreMatchers.equalTo(2514132263))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc11), CoreMatchers.equalTo(2498621061))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc12), CoreMatchers.equalTo(2501530362))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc13), CoreMatchers.equalTo(2495268114))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc14), CoreMatchers.equalTo(2501569219))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc15), CoreMatchers.equalTo(2497924834))
        assertThat(ReflectPlayground().insertionSortAsc(TestCases.tc16), CoreMatchers.equalTo(2504717657))
    }
}
