package com.kentvu.csproblems

import android.app.Application
import com.kentvu.csproblems.dagger.ActivityComponent

class App : Application() {
    companion object {
        lateinit var activityComponent: ActivityComponent
    }

    override fun onCreate() {
        super.onCreate()
        activityComponent = buildActivityComponent()
    }

    private fun buildActivityComponent(): ActivityComponent {
        return DaggerActivityComponent().builder().build();
    }
}