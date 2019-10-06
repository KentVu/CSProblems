package com.kentvu.csproblems

class Repo(data: String) {
    enum class Phase(val sig:String, addImpl: (ProblemBuilder, String) -> Unit) {
        Title(".title", {prb, text -> prb.title = text}),
        Desc(".problem", {prb, text -> prb.title = text}),
        Desc(".solution", {prb, text -> prb.title = text}),
;
        companion object {
            private val values = values()

            fun sigMatch(sig:String) = values.any {it.sig.equals(sig)}
            fun from(str: String): Phase? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }
    val problems = mutableListOf<Problem>()

    fun problems() = problems.toList()

    init {
        data.lineSequence().forEach { line ->
            var currentProblem: ProblemBuilder
            var text = StringBuilder()
            var phase: Phase
            if (Phase.sigMatch(line)) {
                Phase.from(line).apply {
                    when (this) {
                        Phase.Title -> {
                            currentProblem = ProblemBuilder()
                            phase = Phase.Title
                            problems.add(currentProblem.build())
                        }
                        Phase.Desc -> {}
                        else -> {
                            text.append("\n").append(line)
                        }
                    }
                }
            } else { }
            //TODO("Use RegEx™️")
        }
    }
}
