package com.kentvu.csproblems

class CoreLogic(val presenter: MainActivityPresenter) {

    interface MainActivityPresenter/*(val act: MainActivity)*/ {
        fun onActivityCreate()
        fun subscribeEvent(evtListener: UiEvents)
    }
    interface UiEvents {

    }
    private val evtListener: UiEvents = object : UiEvents {
    }

    init {
        presenter. subscribeEvent(evtListener)
    }

}

