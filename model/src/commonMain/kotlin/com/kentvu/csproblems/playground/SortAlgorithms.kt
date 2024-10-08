package com.kentvu.csproblems.playground

import kotlin.collections.toList

typealias AFunc = (Array<out Any?>) -> Any?

class SortAlgorithms : Playground {
  private val algoMap = mapOf<String, AFunc>(
    // https://www.hackerrank.com/challenges/insertion-sort/problem
    "insertionSortDec" to { args/*: IntArray*/ ->
      val arr: IntArray = args[0] as IntArray
      val max = arr.max()!!
      val bit = BIT(max)
      bit.update(arr[0], 1)
      var swaps = 0
      for (i in 1 until arr.size) {
        // Get count of elements smaller than arr[i]
        swaps += bit.sum(arr[i])
        bit.update(arr[i], 1)
      }
      swaps
    },
    "insertionSortAsc" to { args ->
      val arr: IntArray = args[0] as IntArray
      val max = arr.max()!!
      val bit = BIT(max)
      bit.update(arr[arr.lastIndex], 1)
      var swaps = 0L
      for (i in arr.lastIndex - 1 downTo 0) {
        swaps += bit.sum(arr[i] - 1) // Get count of elements smaller than arr[i]
        bit.update(arr[i], 1)
      }
      swaps
    },
  )

  override val algos: List<String>
    get() = algoMap.keys.toList()

  override fun invoke(algo: String, vararg args: Any?): Any? {
    return algoMap[algo]!!.invoke(args)
  }
}

class BIT(max: Int) {
  private val bit: Array<Int>

  init {
    //val max = arr.max()!!
    // build
    bit = Array(max + 1) { i -> 0 }
//        for (v in arr) {
//            bit[v]++
//        }
  }

  fun update(v: Int, inc: Int) {
    var index = v
    // Traverse all ancestors and add 'val'
    while (index <= bit.size - 1) {
      // Add 'val' to current node of BI Tree
      bit[index] += inc

      // Update index to that of parent in update View
      index += index and -index
    }
  }

  fun sum(v: Int): Int {
    var sum = 0 // Initialize result
    // Traverse ancestors of BITree[index]
    var index = v
    while (index > 0) {
      // Add current element of BITree to sum
      sum += bit[index]

      // Move index to parent node in getSum View
      index -= index and -index
    }
    return sum
  }

  fun mark(v: Int) {
    if (bit[v] == 0) {
      update(v, 1)
    }
  }
}
