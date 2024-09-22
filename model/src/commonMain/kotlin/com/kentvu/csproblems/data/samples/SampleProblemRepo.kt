package com.kentvu.csproblems.data.samples

import com.kentvu.csproblems.Problem
import com.kentvu.csproblems.ProblemRepository
import com.kentvu.csproblems.SerializableProblem

class SampleProblemRepo : ProblemRepository {
  companion object {
    val Polish: Problem = SerializableProblem(
        id = "1",
        title = "Reverse Polish Notation",
        description = """
        Good morning! Here's your coding interview problem for today.
        This problem was asked by Jane Street.
        Given an arithmetic expression in Reverse Polish Notation, write a program to evaluate it.
        The expression is given as a list of numbers and operands. For example: `[5, 3, '+']` should return `5 + 3 = 8`.
        For example, `[15, 7, 1, 1, '+', '-', '/', 3, '*', 2, 1, 1, '+', '+', '-']` should return 5, since it is equivalent to `((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1)) = 5`.
        You can assume the given expression is always valid.
      """.trimIndent(),
        sampleInputs = setOf(
          "5, 3, +",
          "15, 7, 1, 1, +, -, /, 3, *, 2, 1, 1, +, +, -",
        ),
    )
    val MapTime: Problem = SerializableProblem(
        id = "2",
        title = "MapTime",
        description = """
          This problem was asked by Stripe.

          Write a map implementation with a get function that lets you retrieve the value of a key at a particular time.

          It should contain the following methods:

          `set(key, value, time)`: sets key to value for t = time.
          `get(key, time)`: gets the key at t = time.
          The map should work like this. If we set a key at a particular time, it will maintain that value forever or until it gets set at a later time. In other words, when we get a key at a time, it should return the value that was set for that key set at the most recent time.

          Consider the following examples:
          `
          d.set(1, 1, 0) # set key 1 to value 1 at time 0
          d.set(1, 2, 2) # set key 1 to value 2 at time 2
          d.get(1, 1) # get key 1 at time 1 should be 1
          d.get(1, 3) # get key 1 at time 3 should be 2

          d.set(1, 1, 5) # set key 1 to value 1 at time 5
          d.get(1, 0) # get key 1 at time 0 should be null
          d.get(1, 10) # get key 1 at time 10 should be 1

          d.set(1, 1, 0) # set key 1 to value 1 at time 0
          d.set(1, 2, 0) # set key 1 to value 2 at time 0
          d.get(1, 0) # get key 1 at time 0 should be 2
          `
        """.trimIndent(),
        sampleInputs = setOf(
          listOf("set:1,1,0", "set:1,2,2", "get:1,1", "get:1,3", ).joinToString("\n"),
          listOf("set:1,1,5", "get:1,0", "get:1,10").joinToString("\n"),
          listOf("set:1,1,0", "set:1,2,0", "get:1,0").joinToString("\n"),
        ),
    )
  }

  override fun loadProblems(): List<Problem> {
    return listOf(Polish, MapTime)
  }
}
