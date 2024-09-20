package com.kentvu.csproblems

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.kentvu.csproblems.components.DefaultRootComponent
import com.kentvu.csproblems.playground.SortAlgorithms
import com.kentvu.csproblems.ui.RootContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import net.withLifecycle

val baseLogger =
  Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), "MmwNotification")

fun main() {

  val lifecycle = LifecycleRegistry()

  val scope = CoroutineScope(Dispatchers.Main).withLifecycle(lifecycle)
  val root = runOnUiThread {
    DefaultRootComponent(
      baseLogger,
      componentContext = DefaultComponentContext(lifecycle = lifecycle),
      mainDispatcher = Dispatchers.Main,
      playground = SortAlgorithms(),
      oldRepo = ProblemRepository.Yaml("TODO"),
    )
  }

  application {
    val windowState = rememberWindowState()

    LifecycleController(lifecycle, windowState)

    Window(
      onCloseRequest = ::exitApplication,
      state = windowState,
      title = "CSProblems",
      onKeyEvent = {
        if (it.key == Key.Q && it.isCtrlPressed) {
          onCloseRequestProc(windowState); true
        } else false
      },
    ) {
      RootContent(root)
    }
  }
}

fun ApplicationScope.onCloseRequestProc(
  windowState: WindowState,
) {
  baseLogger.withTag("DesktopMain").i("onCloseRequestProc")
//  settings.windowState.put(
//    WindowState(
//      windowState.size.width.value,
//      windowState.size.height.value,
//    )
//  )
  exitApplication()
}
