package com.kentvu.csproblems.data

import com.kentvu.csproblems.Language
import com.kentvu.csproblems.ProblemRepository
import com.kentvu.csproblems.Solution
import com.kentvu.csproblems.data.samples.SampleProblemRepo
import com.kentvu.csproblems.data.solution.MapTimeCode
import com.kentvu.csproblems.data.solution.ReversePolishNotation

interface Repository {

  val problems: ProblemRepository
  val solutions: Solutions

  interface Solutions {
    fun load(problemId: String): List<Solution>

    class Sample : Solutions {
      override fun load(problemId: String): List<Solution> {
        return if (problemId == "1") listOf(
          Solution(
            id = "1",
            problemId = problemId,
            lang = Language.Kotlin,
            code = """
              operator fun invoke(input: String): Int {
                val arr = input.split(""${'"'}\s*,\s*""${'"'}.toRegex())
                val i = arr.lastIndex
                when (arr[i]) {
                  "+" -> return arr[i - 1].toInt() + arr[i - 2].toInt()
                  else -> error("Last element is not an operator")
                }
              }
            """.trimIndent(),
            kotlinCode = ReversePolishNotation::invoke,
          )
        ) else if (problemId == "2") listOf(
          Solution(
            id = "2",
            problemId = problemId,
            lang = Language.Kotlin,
            code = """
              ${MapTimeCode::class.qualifiedName}
            """.trimIndent(),
            kotlinCode = MapTimeCode::invoke,
          )
        ) else listOf()
      }
    }
  }

  class Default : Repository {
    override val problems: ProblemRepository = SampleProblemRepo()
    override val solutions: Solutions = Solutions.Sample()
  }
}
