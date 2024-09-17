package com.kentvu.csproblems.components

import co.touchlab.kermit.Logger
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.kentvu.csproblems.ProblemRepository
import com.kentvu.csproblems.components.RootComponent.Child
import com.kentvu.csproblems.components.RootComponent.Config
import com.kentvu.csproblems.components.RootComponent.NavigationEvent
import com.kentvu.csproblems.data.Repository
import com.kentvu.csproblems.playground.Playground
import com.kentvu.csproblems.data.samples.SampleProblemRepo
import com.kentvu.utils.essenty.coroutineScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.SupervisorJob

class DefaultRootComponent(
  private val baseLogger: Logger,
  componentContext: ComponentContext,
  private val mainDispatcher: CoroutineDispatcher,
  private val playground: Playground,
  private val oldRepo: ProblemRepository,
  private val repo: Repository = Repository.Default(),
) : RootComponent, ComponentContext by componentContext {
  private val coroutineScope = coroutineScope(mainDispatcher + SupervisorJob())

  private val navigation = StackNavigation<Config>()

  override val stack: Value<ChildStack<*, Child>> =
    childStack(
      source = navigation,
      serializer = Config.serializer(),
      initialConfiguration = Config.Main,
      handleBackButton = true,
      childFactory = ::child,
    )

  private fun child(config: Config, childComponentContext: ComponentContext): Child =
    when (config) {
      Config.Main -> Child.Main(MainComponent.Default(
        baseLogger, childComponentContext, mainDispatcher, repo
      ) {
        when (it) {
          NavigationEvent.Main.ObsoletedClick -> navigation.push(Config.TestAlgos)
        }
      })

      Config.TestAlgos -> Child.TestAlgos(TestAlgosComponent.Default(
        baseLogger, childComponentContext, mainDispatcher, playground
      ) {
        when (it) {
          NavigationEvent.TestAlgos.ShowDetailClick -> navigation.push(Config.OldProblems)
          NavigationEvent.TestAlgos.BackClicked -> navigation.pop()
        }
      })

      Config.OldProblems -> Child.OldProblems(OldProblemsComponent.Default(
        baseLogger, childComponentContext, mainDispatcher, oldRepo
      ) {
        when (it) {
          NavigationEvent.OldProblems.BackClicked -> navigation.pop()
        }
      })
    }

}
