package com.kentvu.csproblems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.arkivanov.decompose.defaultComponentContext
import com.kentvu.csproblems.components.DefaultRootComponent
import com.kentvu.csproblems.playground.SortAlgorithms
import com.kentvu.csproblems.ui.RootContent
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val baseLogger =
      Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), "MainActivity")
    val root = DefaultRootComponent(
      baseLogger,
      componentContext = defaultComponentContext(),
      mainDispatcher = Dispatchers.Main,
      playground = SortAlgorithms(),
      oldRepo = ProblemRepository.Yaml(
        application.assets.open("input.yaml").bufferedReader().use {
          it.readText()
      }),
    )

    setContent {
      RootContent(component = root)
    }
  }

}

