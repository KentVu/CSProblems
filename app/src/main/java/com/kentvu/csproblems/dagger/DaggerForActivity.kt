package com.kentvu.csproblems.dagger

import androidx.annotation.NonNull
import com.kentvu.csproblems.CoreLogic
import com.kentvu.csproblems.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule(val ma: MainActivity) {

    @Provides
    //@Singleton
    @NonNull
    fun providePresenter(): CoreLogic.UiPresenter = MainActivityPresenter(ma)
}

//@Singleton
@Component(modules = [PresenterModule::class])
interface ActivityComponent {
    fun inject(act: MainActivity)
}

