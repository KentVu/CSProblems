package com.kentvu.csproblems

class Presenter(val log: Log, val view: View, val repo: ProblemRepository) {

    val evtListener = object : View.UiEvents {
        override fun onProblemsCreate() {
            log.d("CoreLogic", "onProblemsCreate")
            val problem = repo.loadProblem()
            view.displayProblem(problem)
        }

        override fun onMainCreate() {
            log.d(TAG, "onMainCreate")
        }

        override fun buttonRunClick(arr: IntArray) {
            log.d(TAG, "buttonRunClick:$arr")
            val result = Playground().insertionSortDec(arr)
            view.displayResult(result)
        }
    }

    companion object {
        private const val TAG = "Presenter"
    }
}