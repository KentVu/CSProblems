package com.kentvu.csproblems

import android.util.Log
import com.kentvu.csproblems.Log as CoreLog

class AndroidLog : CoreLog {
    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

}