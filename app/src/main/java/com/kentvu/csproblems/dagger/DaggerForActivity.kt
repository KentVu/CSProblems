package com.kentvu.csproblems.dagger

import androidx.annotation.NonNull
import com.kentvu.csproblems.MainActivity
import com.kentvu.csproblems.MainActivityPresenter
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    @NonNull
    fun providePresenter() = MainActivityPresenter()
}

@Component(modules = [MainActivityPresenter::class])
interface ActivityComponent {

    fun inject(act: MainActivity): Unit = Unit
}

