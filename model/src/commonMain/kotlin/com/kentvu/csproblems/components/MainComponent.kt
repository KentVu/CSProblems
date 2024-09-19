package com.kentvu.csproblems.components

import co.touchlab.kermit.Logger
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.kentvu.csproblems.Problem
import com.kentvu.csproblems.Solution
import com.kentvu.csproblems.components.RootComponent.NavigationEvent
import com.kentvu.csproblems.data.Repository
import com.kentvu.csproblems.data.solution.AllSolutions
import com.kentvu.utils.ListWithSelection
import com.kentvu.utils.essenty.coroutineScope
import com.kentvu.utils.listWithSelectionOf
import com.kentvu.utils.withSelection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.firstOrNull
import kotlin.coroutines.CoroutineContext
import kotlin.text.orEmpty

interface MainComponent {
  val state: Value<State>

  fun onEvent(event: Event)

  data class State(
    val problems: ListWithSelection<Problem> = listWithSelectionOf(),
    val solutions: ListWithSelection<Solution> = listWithSelectionOf(),
    val input: String = "",
    val result: String = "",
  )

  sealed interface Event {
    data object ObsoletedClick : Event
    data class ProblemSelect(val problem: Problem) : Event {}
    data class InputChange(val value: String) : Event {}
    data class SolutionSelect(val solution: Solution) : Event {}

    data object RunClick : Event
  }

  class Default(
    baseLogger: Logger,
    cContext: ComponentContext,
    mainDispatcher: CoroutineContext,
    private val repo: Repository,
    private val onNavigationEvent: (NavigationEvent.Main) -> Unit,
  ) : MainComponent {
    private val logger = baseLogger.withTag("MainComponent")
    private val scope = cContext.coroutineScope(mainDispatcher + SupervisorJob())
    override val state = MutableValue(State())

    init {
      scope.launch {
        val problems = withContext(Dispatchers.IO) {
          repo.problems.loadProblems()
        }
        state.value = State(
          problems = problems.withSelection(),
          input = problems.firstOrNull()?.sampleInput.orEmpty()
        )
      }
    }

    override fun onEvent(event: Event) {
      when (event) {
        is Event.ObsoletedClick ->
          onNavigationEvent(NavigationEvent.Main.ObsoletedClick)

        is Event.ProblemSelect -> scope.launch {
          val solutions = withContext(Dispatchers.IO) {
            repo.solutions.load(event.problem.id)
          }
          state.update {
            it.copy(
              problems = it.problems.select(event.problem),
              solutions = solutions.withSelection(0),
            )
          }
        }

        is Event.InputChange -> state.update {
          it.copy(input = event.value)
        }

        Event.RunClick -> {
          logger.d("buttonRunClick")
          val code = state.value.solutions.selectedItem?.kotlinCode
          if (code != null) {
            val result = try {
              code.invoke(state.value.input)
            } catch (e: Exception) {
              e
            }
            state.update { it.copy(result = result.toString()) }
          }
        }

        is Event.SolutionSelect -> state.update {
          it.copy(solutions = it.solutions.select(event.solution))
        }
      }
    }
  }
}
