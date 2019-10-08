package com.kentvu.csproblems

import kotlinx.serialization.Serializable

@Serializable
data class Solution(
    val lang: Language,
    val code: String
) {
}