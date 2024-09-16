package com.kentvu.csproblems.samples

import com.kentvu.csproblems.Problem
import com.kentvu.csproblems.ProblemRepository
import com.kentvu.csproblems.SerializableProblem

class SampleProblemRepo : ProblemRepository {
  companion object {
    val Polish: Problem = SerializableProblem(
      title = "Reverse Polish Notation",
      description = """
        Good morning! Here's your coding interview problem for today.
        This problem was asked by Jane Street.
        Given an arithmetic expression in Reverse Polish Notation, write a program to evaluate it.
        The expression is given as a list of numbers and operands. For example: `[5, 3, '+']` should return `5 + 3 = 8`.
        For example, `[15, 7, 1, 1, '+', '-', '/', 3, '*', 2, 1, 1, '+', '+', '-']` should return 5, since it is equivalent to `((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1)) = 5`.
        You can assume the given expression is always valid.
      """.trimIndent(),
      solutions = listOf(),
    )
  }

  override fun loadProblem(): List<Problem> {
    return listOf(Polish)
  }
}
