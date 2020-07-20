package com.kentvu.csproblems

class Presenter(val log: Log, val view: View, val repo: ProblemRepository) {

    val evtListener = object : View.UiEvents {
        override fun onProblemsCreate() {
            log.d("CoreLogic", "onProblemsCreate")
            val problem = repo.loadProblem()
            view.displayProblem(problem)
        }

        private val playground = Playground()

        override fun onMainCreate() {
            log.d(TAG, "onMainCreate")
            view.populateAlgos(playground.algos)
        }

        override fun buttonRunClick(algo: String, arr: IntArray) {
            log.d(TAG, "buttonRunClick:$algo:${arr.joinToString(",")}")
            val result = playground.invoke(algo, arr)
            view.displayResult(result ?: "null")
        }
    }

    companion object {
        private const val TAG = "Presenter"
    }
}