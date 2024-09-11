package com.kentvu.csproblems

import com.charleskorn.kaml.Yaml

class Repo(data: String) {
    val problems = mutableListOf<Problem>()

    fun problems() = problems.toList()

    init {
        val problem = Yaml.default.decodeFromString(SerializableProblem.serializer(), data)
        problems.add(RefinedProblem(problem))
    }
}
