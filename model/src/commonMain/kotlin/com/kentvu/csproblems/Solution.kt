package com.kentvu.csproblems

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Solution(
  val id: String,
  val problemId: String,
  val lang: Language,
  val code: String,
  @Transient
  val kotlinCode: ((String) -> Any)? = null
) {
}
