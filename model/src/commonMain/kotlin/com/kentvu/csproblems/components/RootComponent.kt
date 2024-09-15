package com.kentvu.csproblems.components

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kentvu.csproblems.components.TestAlgosComponent
import kotlinx.serialization.Serializable

interface RootComponent {

  val stack: Value<ChildStack<*, Child>>

  sealed class Child {
    class Main(val component: MainComponent) : Child()
    class TestAlgos(val component: TestAlgosComponent) : Child()
    class OldProblems(val component: OldProblemsComponent) : Child()
  }

  @Serializable
  sealed interface Config {
    @Serializable
    data object Main : Config

    @Serializable
    data object TestAlgos : Config

    @Serializable
    data object OldProblems : Config
  }

  sealed interface NavigationEvent {
    sealed interface Main : NavigationEvent {
      data object ObsoletedClick : Main
    }

    sealed interface TestAlgos : NavigationEvent {
      data object BackClicked : TestAlgos
      data object ShowDetailClick : TestAlgos
    }

    interface OldProblems : NavigationEvent {
      data object BackClicked : OldProblems
    }
  }
}
