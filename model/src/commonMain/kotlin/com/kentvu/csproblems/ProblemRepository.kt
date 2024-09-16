package com.kentvu.csproblems

interface ProblemRepository {
    fun loadProblem(): List<Problem>

    class Yaml(val data: String) : ProblemRepository {
      override fun loadProblem(): List<Problem> {
        return YamlProblemLoad(data).problems
      }
    }

}
