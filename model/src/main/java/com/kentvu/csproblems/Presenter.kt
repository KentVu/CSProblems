package com.kentvu.csproblems

class Presenter(val log: Log, val view: View, val repo: ProblemRepository) {
    val evtListener = object : View.UiEvents {
        override fun onActivityCreate() {
            log.d("CoreLogic", "onActivityCreate")
            val problem = repo.loadProblem()
            view.display(problem)
        }
    }
}