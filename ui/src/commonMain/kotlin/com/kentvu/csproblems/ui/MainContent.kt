package com.kentvu.csproblems.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.kentvu.csproblems.components.MainComponent
import com.kentvu.csproblems.components.MainComponent.Event
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainContent(component: MainComponent) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(stringResource(Res.string.app_title)) },
      )
    },
  ) { contentPadding ->
    Column(
      modifier = Modifier.padding(contentPadding).fillMaxSize(),
    ) {
      val state: MainComponent.State by component.state.subscribeAsState()
      DropdownMenu(
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
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp,end = 8.dp),
        horizontalArrangement = Arrangement.End,
      ) {
        Button(
          onClick = {component.onEvent(Event.RunClick)},
          enabled = state.algos.selectedItem != null,
        ) {
          Text("Run")
        }
      }
      Text(state.result, Modifier.padding(start=16.dp,top=16.dp))
      Button(
        onClick = {component.onEvent(Event.ShowDetailClick)},
      ) {
        Text("Show Detail")
      }
    }
  }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun <T> DropdownMenu(
  editable: Boolean,
  modifier: Modifier = Modifier,
  label: String,
  initialText: String,
  enabled: Boolean,
  options: List<T>,
  renderer: (T) -> String,
  onValueChange: (String) -> Unit = {},
  onSelect: (T) -> Unit,
) {
  var expanded by remember { mutableStateOf(false) }
  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { expanded = enabled && it },
    modifier = modifier,
  ) {
    var text by remember { mutableStateOf(/*TextFieldValue*/(initialText)) }
    Row(
      Modifier.width(IntrinsicSize.Max),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      TextField(
        // The `menuAnchor` modifier must be passed to the text field to handle
        // expanding/collapsing the menu on click. A read-only text field has
        // the anchor type `PrimaryNotEditable`.
        modifier = Modifier.menuAnchor(/*MenuAnchorType.PrimaryNotEditable*/)
          .weight(1f),
        value = if (editable) text else initialText,
        onValueChange = { text = it },
        readOnly = !editable,
        enabled = enabled,
        singleLine = !editable,
        label = { Text(label) },
        trailingIcon = {
          ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
        },
        colors = ExposedDropdownMenuDefaults.textFieldColors(),
      )
      if (editable) {
        IconButton({ onValueChange(text) }) { Icon(Icons.Filled.Check, "Confirm") }
      }
    }
    ExposedDropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false },
    ) {
      options.forEach { option ->
        DropdownMenuItem(
          text = { Text(renderer(option), style = MaterialTheme.typography.bodyLarge) },
          onClick = {
            onSelect(option)
            text = renderer(option)
            expanded = false
          },
          contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
        )
      }
    }
  }
}
