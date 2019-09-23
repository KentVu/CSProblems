package com.kentvu.csproblems

class Repo(data: String) {
    val problems = mutableListOf<Problem>()

    fun problems() = problems.toList()

    init {
        data.lineSequence().forEach {
            var currentProblem = ProblemBuilder()
            when(it) {
                ".title" -> {
                    currentProblem = ProblemBuilder()
                    problems.add(currentProblem.build())
                }
                else -> ""
            }
            TODO("Use RegEx™️")
        }
    }
}
