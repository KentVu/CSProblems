package com.kentvu.csproblems

import kotlinx.serialization.Serializable

interface Problem {
  val id: String
  val title: String
  val description: String
  val sampleInput: String
}

class RefinedProblem(
  rawProblem: Problem
) : Problem by rawProblem {
    override val title = rawProblem.title.trimEnd()
    override val description = rawProblem.description.trimEnd()
}

@Serializable
internal data class SerializableProblem(
  override val id: String,
  override val title: String,
  override val description: String,
  override val sampleInput: String,
) : Problem { }

