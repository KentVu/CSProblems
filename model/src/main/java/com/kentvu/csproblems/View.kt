package com.kentvu.csproblems

interface View {
    interface UiEvents {
        fun onProblemsCreate()
        fun onMainCreate()
        fun buttonRunClick(algo: String, arr: IntArray)
    }

    fun displayProblem(problem: Problem)
    fun displayResult(result: Any)
    fun populateAlgos(algos: List<String>)
}
