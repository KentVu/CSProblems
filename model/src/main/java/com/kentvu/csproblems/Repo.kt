package com.kentvu.csproblems

class Repo(data: String) {
    enum class Phase(val sig:String, addImpl: (ProblemBuilder, String) -> Unit) {
        Title(".title", {prb, text -> prb.title = text});
        companion object {
            private val values = values()

            fun sigMatch(sig:String) = values.any {it.sig.equals(sig)}
        }
    }
    val problems = mutableListOf<Problem>()

    fun problems() = problems.toList()

    init {
        data.lineSequence().forEach { line ->
            var currentProblem = ProblemBuilder()
            if (Phase.sigMatch(line)) {
                currentProblem = ProblemBuilder()
                problems.add(currentProblem.build())
            }
            else ""
            TODO("Use RegEx™️")
        }
    }
}
