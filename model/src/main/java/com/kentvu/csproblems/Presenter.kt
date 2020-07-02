package com.kentvu.csproblems

class Presenter(val log: Log, val view: View, val repo: ProblemRepository) {
    val evtListener = object : View.UiEvents {
        override fun onProblemsCreate() {
            log.d("CoreLogic", "onProblemsCreate")
            val problem = repo.loadProblem()
            view.displayProblem(problem)
        }

        override fun onMainCreate(arr: Array<Int>) {
            log.d("Presenter", "onMainCreate")
            val result = Playground().insertionSortDec(arr)
            view.displayResult(result)
        }
    }
}