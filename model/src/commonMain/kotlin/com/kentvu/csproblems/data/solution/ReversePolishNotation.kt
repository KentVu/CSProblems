package com.kentvu.csproblems.data.solution

object ReversePolishNotation: KotlinSolution {
  sealed interface Kind {
  }

  operator fun invoke(input: String): Int {
    val arr = input.split("""\s*,\s*""".toRegex())
    val i = arr.lastIndex
    when (arr[i]) {
      "+" -> return arr[i - 1].toInt() + arr[i - 2].toInt()
      else -> error("Last element is not an operator")
    }
  }
}
