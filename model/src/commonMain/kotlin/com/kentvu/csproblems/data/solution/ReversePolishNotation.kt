package com.kentvu.csproblems.data.solution

object ReversePolishNotation: KotlinSolution {
  sealed interface Kind {
  }

  operator fun invoke(input: String): Int {
    val arr = input.split("""\s*,\s*""".toRegex())
    val i = arr.lastIndex
    return when (arr[i]) {
      "+", "-", "*", "/" -> {
        calc(arr[i - 2], arr[i - 1], arr[i])
      }
      else -> error("Last element is not an operator")
    }
  }

  private fun calc(lhs: String, rhs: String, opr: String): Int {
    val iLhs = lhs.toInt()
    val iRhs = rhs.toInt()
    return when(opr) {
      "+" -> iLhs + iRhs
      "-" -> iLhs - iRhs
      "*" -> iLhs * iRhs
      "/" -> iLhs / iRhs
      else -> error("Unknown operator $opr")
    }
  }
}
