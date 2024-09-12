package com.kentvu.csproblems.components

import co.touchlab.kermit.Logger
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.kentvu.csproblems.Playground
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
import com.kentvu.csproblems.components.RootComponent.NavigationEvent.Main as NavigationEventMain

interface MainComponent {
  val state: Value<State>

  fun onEvent(event: Event)

  data class State(
    val algos: ListWithSelection<String> = listWithSelectionOf(),
    val input: String = "",
    val result: String = "",
  )

  sealed interface Event {
    data class AlgoSelect(val algo: String) : Event {}
    data class InputChange(val newInput: String) : Event
    data object RunClick : Event
    data object ShowDetailClick : Event
  }

  class Default(
    baseLogger: Logger,
    cContext: ComponentContext,
    mainDispatcher: CoroutineContext,
    private val playground: Playground,
    private val onNavigationEvent: (NavigationEventMain) -> Unit,
  ) : MainComponent {
    private val logger = baseLogger.withTag("MainComponent")
    private val scope = cContext.coroutineScope(mainDispatcher + SupervisorJob())
    override val state = MutableValue(State())

    init {
      scope.launch {
        //val algos = async(Dispatchers.IO) { playground.algos }
        //algos.await().getCompleted()
        val algos = withContext(Dispatchers.IO) { playground.algos }
        state.update { it.copy(algos = algos.withSelection()) }
      }
    }

    override fun onEvent(event: Event) {
      when (event) {
        is Event.AlgoSelect -> /*scope.launch*/ {
          state.update {
            it.copy(
              algos = it.algos.select(event.algo)
            )
          }
        }

        is Event.InputChange -> state.update {
          it.copy(input = event.newInput)
        }

        Event.RunClick -> {
          //val (algo, arr)
          val algo = state.value.algos.selectedItem!!
          val arr = state.value.input.split("""\s*,\s*""".toRegex()).map { it.toInt() }.toIntArray()
          logger.d( "buttonRunClick:$algo:${arr.joinToString(",")}")
          val result = playground.invoke(algo, arr)
          state.update{it.copy(result=result?.toString() ?: "null")}
          }

        Event.ShowDetailClick ->
          onNavigationEvent(NavigationEventMain.ShowDetailClick)
      }
    }

  }
}
