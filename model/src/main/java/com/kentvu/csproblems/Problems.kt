package com.kentvu.csproblems

import kotlinx.serialization.Serializable

interface Problem {
    val title: String
    val description: String
    val solutions: List<Solution>
}

class RefinedProblem(rawProblem: Problem ) : Problem {
    override val title: String
    override val description: String
    override val solutions: List<Solution>

    init {
        rawProblem.apply {
            this@RefinedProblem.title = title.trimEnd()
            this@RefinedProblem.description = description.trimEnd()
            this@RefinedProblem.solutions = solutions
        }
    }
}

@Serializable
internal data class SerializableProblem(
    override val title: String,
    override val description: String,
    override val solutions: List<Solution>) : Problem {
}

