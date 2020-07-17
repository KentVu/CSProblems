package com.kentvu.csproblems

interface View {
    interface UiEvents {
        fun onProblemsCreate()
        fun onMainCreate()
        fun buttonRunClick(arr: IntArray)
    }

    fun displayProblem(problem: Problem)
    fun displayResult(result: Any)
}
