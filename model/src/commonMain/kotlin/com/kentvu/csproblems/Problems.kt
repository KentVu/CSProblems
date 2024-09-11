package com.kentvu.csproblems

import kotlinx.serialization.Serializable

interface Problem {
    val title: String
    val description: String
    val solutions: List<Solution>
}

class RefinedProblem private constructor(
    override val title: String,
    override val description: String,
    override val solutions: List<Solution>
) : Problem {

    constructor(rawProblem: Problem): this(
        rawProblem.title.trimEnd(),
        rawProblem.description.trimEnd(),
        rawProblem.solutions
    )
}

@Serializable
internal data class SerializableProblem(
    override val title: String,
    override val description: String,
    override val solutions: List<Solution>) : Problem {
}

