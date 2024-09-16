package com.kentvu.csproblems.components

import co.touchlab.kermit.Logger
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.kentvu.csproblems.Problem
import com.kentvu.csproblems.ProblemRepository
import com.kentvu.csproblems.components.RootComponent.NavigationEvent
import com.kentvu.utils.essenty.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.first
import kotlin.coroutines.CoroutineContext

interface OldProblemsComponent {
  val state: Value<State>

  fun onEvent(event: Event)

  data class State(
    val problem : Problem? = null,
  )

  sealed interface Event {
    data object BackClicked : Event
  }

  class Default(
    baseLogger: Logger,
    cContext: ComponentContext,
    mainDispatcher: CoroutineContext,
    private val repo: ProblemRepository,
    private val onNavigationEvent: (NavigationEvent.OldProblems) -> Unit,
  ): OldProblemsComponent {
    private val logger = baseLogger.withTag("ProblemsComponent")
    private val scope = cContext.coroutineScope(mainDispatcher + SupervisorJob())
    override val state = MutableValue(State())

    init {
      scope.launch {
        logger.d( "onProblemsCreate")
        val problem = withContext(Dispatchers.IO) {
          repo.loadProblem()
        }
        state.value = State(problem = problem.first())
      }
    }

    override fun onEvent(event: Event) {
      when (event) {
        Event.BackClicked ->
          onNavigationEvent(NavigationEvent.OldProblems.BackClicked)
      }
    }
  }
}
