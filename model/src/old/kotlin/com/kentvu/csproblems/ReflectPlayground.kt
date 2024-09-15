package com.kentvu.csproblems

import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberFunctions

class ReflectPlayground : Playground {
    @Target(AnnotationTarget.FUNCTION)
    annotation class AlgoFunction

    override val algos: List<String> get() {
        return ReflectPlayground::class.memberFunctions.filter {
            it.hasAnnotation<AlgoFunction>()
        }.map { it.name }.toList()
    }

    // https://www.hackerrank.com/challenges/insertion-sort/problem
    @AlgoFunction
    fun insertionSortDec(arr: IntArray): Int {
        val max = arr.max()!!
        val bit = BIT(max)
        bit.update(arr[0], 1)
        var swaps = 0
        for (i in 1 until arr.size) {
            // Get count of elements smaller than arr[i]
            swaps += bit.sum(arr[i])
            bit.update(arr[i], 1)
        }
        return swaps
    }

    @AlgoFunction
    fun insertionSortAsc(arr: IntArray): Long {
        val max = arr.max()!!
        val bit = BIT(max)
        bit.update(arr[arr.lastIndex], 1)
        var swaps = 0L
        for (i in arr.lastIndex - 1 downTo 0) {
            swaps += bit.sum(arr[i] - 1) // Get count of elements smaller than arr[i]
            bit.update(arr[i], 1)
        }
        return swaps
    }

    override fun invoke(algo: String, vararg args: Any?): Any? {
        return ReflectPlayground::class.memberFunctions.first { it.name == algo }.run {
            call(this@ReflectPlayground, *args)
        }
    }

}

