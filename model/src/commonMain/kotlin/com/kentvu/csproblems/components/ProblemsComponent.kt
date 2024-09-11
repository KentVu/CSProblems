package com.kentvu.csproblems.components

import com.arkivanov.decompose.ComponentContext
import kotlin.coroutines.CoroutineContext

interface ProblemsComponent {

  class Default(
    cContext: ComponentContext,
    mainDispatcher: CoroutineContext,
  ): ProblemsComponent
}
