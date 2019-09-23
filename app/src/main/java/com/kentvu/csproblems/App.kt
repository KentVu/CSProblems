package com.kentvu.csproblems

import android.app.Application
import android.content.Context
import com.kentvu.csproblems.dagger.ActivityComponent
import com.kentvu.csproblems.dagger.DaggerActivityComponent

class App : Application() {
    companion object {
        fun get(ctx: Context) = ctx.applicationContext as App
    }

    val activityComponent: ActivityComponent by lazy {
        buildActivityComponent()
    }

    override fun onCreate() {
        super.onCreate()
    }

    private fun buildActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder().build();
    }
}