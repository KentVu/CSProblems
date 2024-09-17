package com.kentvu.csproblems

interface ProblemRepository {
    fun loadProblems(): List<Problem>

    class Yaml(val data: String) : ProblemRepository {
      override fun loadProblems(): List<Problem> {
        return YamlProblemLoad(data).problems
      }
    }
}
