package com.kentvu.csproblems

class ProblemBuilder {
    private val description: String? = null
    private val title: String? = null
    fun build(): Problem {
        if (title == null) {
            throw IllegalStateException("title is null")
        }
        return Problem(title, description ?: "null")
    }

}
