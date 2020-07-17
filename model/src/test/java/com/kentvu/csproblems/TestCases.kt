package com.kentvu.csproblems

import java.util.*

data class TestCase(val input: Array<Int>, val expect: Int)
object TestCases {
    val tc1: Array<Int> by lazy {
        javaClass.classLoader.getResourceAsStream("input08.txt").use {stream ->
            val scan = Scanner(stream)
            val t = scan.nextLine().trim().toInt()
            val n = scan.nextLine().trim().toInt()
            scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
        }
    }
    val tc2: Lazy<Array<Int>>
    lateinit var tc3: Array<Int>
    lateinit var tc4: Array<Int>
    lateinit var tc5: Array<Int>
    lateinit var tc6: Array<Int>
    lateinit var tc7: Array<Int>
    lateinit var tc8: Array<Int>
    lateinit var tc9: Array<Int>
    lateinit var tc10: Array<Int>
    lateinit var tc11: Array<Int>
    lateinit var tc12: Array<Int>
    lateinit var tc13: Array<Int>
    lateinit var tc14: Array<Int>
    lateinit var tc15: Array<Int>
    lateinit var tc16: Array<Int>
    init {
        tc2 = lazy {
            Scanner(javaClass.classLoader.getResourceAsStream("input12.txt")!!).use { scan ->
                val t = scan.nextLine().trim().toInt()
                val n = scan.nextLine().trim().toInt()
                scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            }
        }
        val scanner = Scanner(javaClass.classLoader.getResourceAsStream("input12.txt")!!)
        scanner.use { scan ->
            val t = scan.nextLine().trim().toInt()
            var n = scan.nextLine().trim().toInt()
            var skip = scan.run {
                nextLine() // skip a case
                //nextLine()
            }
            n = scan.nextLine().trim().toInt()
            tc3 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc4 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc5 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc6 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc7 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc8 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc9 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc10 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc11 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc12 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc13 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc14 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc15 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
            n = scan.nextLine().trim().toInt()
            tc16 = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
        }
    }
}
