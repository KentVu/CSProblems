package com.kentvu.csproblems

import java.util.*

object TestCases {
    val tc1: Array<Int> by lazy {
        val scan = Scanner(javaClass.classLoader.getResourceAsStream("input08.txt"))
        val t = scan.nextLine().trim().toInt()
        val n = scan.nextLine().trim().toInt()
        scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
    }
}
