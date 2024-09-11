package com.kentvu.csproblems.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.kentvu.csproblems.components.RootComponent
import com.kentvu.csproblems.components.RootComponent.Child

@Composable
fun RootContent(
  component: RootComponent,
  modifier: Modifier = Modifier,
) {
  MaterialTheme {
    Surface(modifier = modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)) {
      Children(
        stack = component.stack,
        modifier = Modifier.fillMaxSize(),
        animation = stackAnimation(fade() + scale())
      ) {
        when (val instance = it.instance) {
          is Child.Main -> MainContent(component = instance.component)
          is Child.Problems -> ProblemsContent(component = instance.component)
        }
      }
    }
  }
}

