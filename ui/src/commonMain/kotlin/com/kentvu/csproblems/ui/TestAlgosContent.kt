package com.kentvu.csproblems.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.kentvu.csproblems.components.TestAlgosComponent
import com.kentvu.csproblems.components.TestAlgosComponent.Event
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TestAlgosContent(component: TestAlgosComponent) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(stringResource(Res.string.app_title)) },
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
      modifier = Modifier.padding(contentPadding).fillMaxSize(),
    ) {
      val state: TestAlgosComponent.State by component.state.subscribeAsState()
      DropdownMenuBox(
        false,
        Modifier.padding(start = 16.dp, top = 8.dp),
        "Algos",
        state.algos.selectedItem ?: "--",
        true,
        state.algos,
        { it },
      ) { component.onEvent(Event.AlgoSelect(it)) }
      TextField(
        value = state.input,
        onValueChange = { component.onEvent(Event.InputChange(it)) },
        modifier = Modifier.padding(start = 24.dp, top = 8.dp),
        label = { Text(text = "sample test case input here") },
        placeholder = { Text("1, 4, 3, 6, 7, 2, ...") }
      )
      Row(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.End,
      ) {
        Button(
          onClick = { component.onEvent(Event.RunClick) },
          enabled = state.algos.selectedItem != null,
        ) {
          Text("Run")
        }
      }
      Text(state.result, Modifier.padding(start = 16.dp, top = 16.dp))
      Button(
        onClick = { component.onEvent(Event.ShowDetailClick) },
      ) {
        Text("Show Detail")
      }
    }
  }
}

