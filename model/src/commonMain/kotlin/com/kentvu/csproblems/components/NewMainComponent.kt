package com.kentvu.csproblems.components

import co.touchlab.kermit.Logger
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.kentvu.csproblems.components.RootComponent.NavigationEvent
import com.kentvu.utils.essenty.coroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface MainComponent {
  val state: Value<State>

  fun onEvent(event: Event)

  data class State(val sth: String = "")

  sealed interface Event {
    data object ObsoletedClick : Event
  }

  class Default(
    baseLogger: Logger,
    cContext: ComponentContext,
    mainDispatcher: CoroutineContext,
    private val onNavigationEvent: (NavigationEvent.Main) -> Unit,
  ) : MainComponent {
    private val logger = baseLogger.withTag("MainComponent")
    private val scope = cContext.coroutineScope(mainDispatcher + SupervisorJob())
    override val state = MutableValue(State())

    override fun onEvent(event: Event) {
      when (event) {
        is Event.ObsoletedClick ->
          onNavigationEvent(NavigationEvent.Main.ObsoletedClick)
      }
    }
  }
}
