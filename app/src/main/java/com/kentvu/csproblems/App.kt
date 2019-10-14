package com.kentvu.csproblems

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        fun get(ctx: Context) = ctx.applicationContext as App
    }
}