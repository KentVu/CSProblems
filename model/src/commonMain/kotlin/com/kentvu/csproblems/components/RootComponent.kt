package com.kentvu.csproblems.components

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface RootComponent {

  val stack: Value<ChildStack<*, Child>>

  sealed class Child {
    class Main(val component: MainComponent) : Child()
    class Problems(val component: ProblemsComponent) : Child()
  }

  @Serializable
  sealed interface Config {
    @Serializable
    data object Main : Config
    @Serializable
    data object Problems : Config
  }

  sealed interface NavigationEvent {
    sealed interface Main: NavigationEvent {
      data object ShowDetailClick : Main
    }

    interface Problems: NavigationEvent {
      data object BackClicked : Problems
    }
  }
}
