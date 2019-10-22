package com.kentvu.csproblems

interface View {
    interface UiEvents {
        fun onActivityCreate()
    }

    fun display(problem: Problem)
}
