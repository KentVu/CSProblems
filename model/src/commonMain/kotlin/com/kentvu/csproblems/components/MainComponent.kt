package com.kentvu.csproblems.components

import co.touchlab.kermit.Logger
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.kentvu.csproblems.Problem
import com.kentvu.csproblems.ProblemRepository
import com.kentvu.csproblems.components.RootComponent.NavigationEvent
import com.kentvu.utils.ListWithSelection
import com.kentvu.utils.essenty.coroutineScope
import com.kentvu.utils.listWithSelectionOf
import com.kentvu.utils.withSelection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

interface MainComponent {
  val state: Value<State>

  fun onEvent(event: Event)

  data class State(
    val problems : ListWithSelection<Problem> = listWithSelectionOf(),
  )

  sealed interface Event {
    data object ObsoletedClick : Event
    data class ProblemSelect(val problem: Problem) : Event {}
  }

  class Default(
    baseLogger: Logger,
    cContext: ComponentContext,
    mainDispatcher: CoroutineContext,
    private val repo: ProblemRepository,
    private val onNavigationEvent: (NavigationEvent.Main) -> Unit,
  ) : MainComponent {
    private val logger = baseLogger.withTag("MainComponent")
    private val scope = cContext.coroutineScope(mainDispatcher + SupervisorJob())
    override val state = MutableValue(State())

    init {
      scope.launch {
        val problem = withContext(Dispatchers.IO) {
          repo.loadProblem()
        }
        state.value = State(problems = problem.withSelection())
      }
    }

    override fun onEvent(event: Event) {
      when (event) {
        is Event.ObsoletedClick ->
          onNavigationEvent(NavigationEvent.Main.ObsoletedClick)

        is Event.ProblemSelect -> state.update {
          it.copy(problems = it.problems.select(event.problem))
        }
      }
    }
  }
}
