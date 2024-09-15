package com.kentvu.csproblems.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.kentvu.csproblems.components.OldProblemsComponent
import com.kentvu.csproblems.components.OldProblemsComponent.Event
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OldProblemsContent(component: OldProblemsComponent) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(stringResource(Res.string.problems_title)) },
        navigationIcon = {
          IconButton(onClick = { component.onEvent(Event.BackClicked) }) {
            Icon(
              imageVector = Icons.AutoMirrored.Default.ArrowBack,
              contentDescription = "Back button",
            )
          }
        },
      )
    },
  ) { contentPadding ->
    Column(
      modifier = Modifier.fillMaxSize().padding(contentPadding).padding(start = 8.dp)
        .verticalScroll(rememberScrollState()),
      verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
      val state: OldProblemsComponent.State by component.state.subscribeAsState()
      when (val problem = state.problem) {
          null -> CircularProgressIndicator()
          else -> {
            Text("Title")
            Text(problem.title, style = MaterialTheme.typography.headlineMedium)
            Text("Description")
            Text(problem.description, style = MaterialTheme.typography.headlineMedium)
            Text("Solution")
            Text("SolutionLang", Modifier.padding(start = 8.dp))
            Text(problem.solutions[0].lang.displayName, style = MaterialTheme.typography.headlineMedium)
            Text(problem.solutions[0].code, style = MaterialTheme.typography.bodyMedium)
          }
      }
    }
  }
}
