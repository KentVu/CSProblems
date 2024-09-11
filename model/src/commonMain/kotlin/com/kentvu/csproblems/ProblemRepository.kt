package com.kentvu.csproblems

interface ProblemRepository {
    fun loadProblem(): Problem

    class TODO(val data: String) : ProblemRepository {
      override fun loadProblem(): Problem {
        return Repo(data).problems.first()
      }

    }

}
