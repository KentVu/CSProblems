package com.kentvu.csproblems

class CoreLogic(val log: Log, val presenter: UiPresenter) {

    interface UiPresenter/*(val act: MainActivity)*/ {
    }
    interface UiEvents {
        fun onActivityCreate()
    }
    val evtListener: UiEvents = object : UiEvents {
        override fun onActivityCreate() {
            log.d("CoreLogic", "onActivityCreate")
        }
    }

    init {
    }

}

