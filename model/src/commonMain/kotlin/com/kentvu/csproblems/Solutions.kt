package com.kentvu.csproblems

import kotlinx.serialization.Serializable

@Serializable
data class Solution(
  val id: String,
  val problemId: String,
  val lang: Language,
  val code: String,
) {
}
