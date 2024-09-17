package com.kentvu.csproblems.data.solution

import com.kentvu.csproblems.data.samples.SampleProblemRepo

val AllSolutions: Map<String, (String) -> Int> = mapOf(
  SampleProblemRepo.Polish.id to ReversePolishNotation::invoke
)
