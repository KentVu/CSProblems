package com.kentvu.csproblems

import android.app.Application
import android.content.Context
import com.kentvu.csproblems.dagger.ActivityComponent
import com.kentvu.csproblems.dagger.DaggerActivityComponent
import com.kentvu.csproblems.dagger.PresenterModule

class App : Application() {
    companion object {
        fun get(ctx: Context) = ctx.applicationContext as App
    }

    val activityComponent: ActivityComponent by lazy {
        buildActivityComponent()
    }

    private fun buildActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder().presenterModule(PresenterModule()).build();
    }
}