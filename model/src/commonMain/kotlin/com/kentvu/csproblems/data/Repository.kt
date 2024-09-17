package com.kentvu.csproblems.data

import com.kentvu.csproblems.Language
import com.kentvu.csproblems.ProblemRepository
import com.kentvu.csproblems.Solution
import com.kentvu.csproblems.data.samples.SampleProblemRepo

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
            code = "",
          )
        ) else TODO()
      }
    }
  }

  class Default : Repository {
    override val problems: ProblemRepository = SampleProblemRepo()
    override val solutions: Solutions = Solutions.Sample()
  }
}
