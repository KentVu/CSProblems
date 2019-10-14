package com.kentvu.csproblems.dagger

import com.kentvu.csproblems.AndroidLog
import com.kentvu.csproblems.CoreLogic
import com.kentvu.csproblems.MainActivity

class MainActivityPresenter(val ma: MainActivity) : CoreLogic.UiPresenter {
    val coreLogic = CoreLogic(AndroidLog(), this)
    val evtListener = object : CoreLogic.UiEvents by coreLogic.evtListener {

    }

    init {
    }

}
