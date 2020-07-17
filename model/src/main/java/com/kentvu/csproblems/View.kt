package com.kentvu.csproblems

interface View {
    interface UiEvents {
        fun onProblemsCreate()
        fun onMainCreate()
        fun buttonRunClick(arr: Array<Int>)
    }

    fun displayProblem(problem: Problem)
    fun displayResult(result: Any)
}
