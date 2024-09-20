package com.kentvu.csproblems.data.solution

object ReversePolishNotation: KotlinSolution {
  val ops: Map<String, (lhs: Int, rhs: Int) -> Int> = mapOf(
    "+" to { lhs, rhs -> lhs + rhs },
    "-" to { lhs, rhs -> lhs - rhs },
    "*" to { lhs, rhs -> lhs * rhs },
    "/" to { lhs, rhs -> lhs / rhs },
  )
  operator fun invoke(input: String): Int {
    val arr = input.split("""\s*,\s*""".toRegex()).toTypedArray()
    val i = arr.lastIndex
    return rpn(arr, i, i - 2, i - 1)
  }

  private fun rpn(arr: Array<String>, opi: Int, lhsi: Int, rhsi: Int): Int {
    val opr = arr[opi]
    return when  {
      ops.containsKey(opr) -> {
        calc(arr[lhsi].toInt(), arr[rhsi].toInt(), opr)
      }
      else -> error("Last element is not an operator")
    }
  }

  private fun calc(lhs: Int, rhs: Int, opr: String): Int {
    return when {
      ops.containsKey(opr) -> ops[opr]!!(lhs, lhs)
      else -> error("Unknown operator $opr")
    }
  }
}
