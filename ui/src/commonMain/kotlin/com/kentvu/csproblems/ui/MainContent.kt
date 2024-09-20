package com.kentvu.csproblems.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.kentvu.csproblems.Solution
import com.kentvu.csproblems.components.MainComponent
import com.kentvu.csproblems.components.MainComponent.Event
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import org.jetbrains.compose.resources.stringResource
import kotlin.let

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainContent(component: MainComponent) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(stringResource(Res.string.app_title)) },
        actions = {
          var showMenu by remember { mutableStateOf(false) }
          IconButton(onClick = { showMenu = !showMenu }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Show Welcome screen")
          }
          DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
          ) {
            DropdownMenuItem(
              onClick = { component.onEvent(Event.ObsoletedClick) },
              leadingIcon = {
                Icon(
                  Icons.Default.Info,
                  contentDescription = stringResource(Res.string.obsoleted)
                )
            },
              text = { Text(stringResource(Res.string.obsoleted)) }
            )
          }
        },
      )
    }
  ) { contentPadding ->
    Column(
      modifier = Modifier.padding(contentPadding).fillMaxSize(),
      verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
      val state: MainComponent.State by component.state.subscribeAsState()
      DropdownMenuBox(
        false,
        Modifier.padding(start = 16.dp, top = 8.dp),
        "Problems",
        state.problems.selectedItem?.title ?: "--",
        true,
        state.problems,
        { it.title },
      ) { component.onEvent(Event.ProblemSelect(it)) }
      state.problems.selectedItem?.let { problem ->
        val pagerState = rememberPagerState(pageCount = { 2 })
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
          when (page) {
            0 ->
              ProblemDescription(problem.description)
            1 -> state.solutions.selectedItem?.let { solution ->
              SolutionDescription(solution)
            } ?: Text("Select solution!")
            else -> { Text("WTH?page=$page")}
          }
        }
        Text(
          when (pagerState.currentPage) {
            0 -> "Problem description -->"
            else -> "<-- Solution"
          }
        )
        TextField(
          value = state.input,
          onValueChange = { component.onEvent(Event.InputChange(it)) },
          modifier = Modifier.padding(start = 24.dp, top = 8.dp),
          placeholder = { Text("1, 4, 3, 6, 7, 2, ...") }
        )
        DropdownMenuBox(
          true,
          Modifier.padding(start = 24.dp, top = 8.dp),
          "sample test case input here",
          state.input,
          true,
          problem.sampleInputs,
          {it},
          {component.onEvent(Event.InputChange(it))}
        ) { component.onEvent(Event.InputChange(it)) }
        val solutionRenderer: (Solution) -> String = { "${it.id} - ${it.lang.displayName}" }
        DropdownMenuBox(
          false,
          Modifier.padding(start = 16.dp, top = 8.dp),
          "Solutions",
          state.solutions.selectedItem?.let { solutionRenderer(it) } ?: "--",
          true,
          state.solutions,
          solutionRenderer,
        ) { component.onEvent(Event.SolutionSelect(it)) }
        Row(
          modifier = Modifier.fillMaxWidth().padding(top = 8.dp, end = 8.dp),
          horizontalArrangement = Arrangement.End,
        ) {
          Button(
            onClick = { component.onEvent(Event.RunClick) },
            enabled = state.input.isNotEmpty() && state.solutions.selectedItem != null,
          ) {
            Text("Run")
          }
        }
        Text(state.result, Modifier.padding(start = 16.dp, top = 16.dp))
      }
    }
  }
}

@Composable
private fun ProblemDescription(
  description: String,
  modifier: Modifier = Modifier,
) {
  val rtxState = rememberRichTextState()
  rtxState.setMarkdown(description)

  RichTextEditor(
    modifier = modifier,
    state = rtxState,
    readOnly = true,
  )
}

@Composable
private fun SolutionDescription(
  solution: Solution,
  modifier: Modifier = Modifier,
) {
  val rtxState = rememberRichTextState()
  val md = "# ${solution.id}\n" +
    //"```\n" + //${solution.lang}
    "`" +
    solution.code +
    "`"
  rtxState.setMarkdown(md)
  //rtxState.addCodeSpan()
  //rtxState.setText(solution.code)

  RichTextEditor(
    modifier = modifier,
    state = rtxState,
    readOnly = true,
  )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun <T> DropdownMenuBox(
  editable: Boolean,
  modifier: Modifier = Modifier,
  label: String,
  initialText: String,
  enabled: Boolean,
  options: Collection<T>,
  renderer: (T) -> String,
  onConfirmValue: (String) -> Unit = {},
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
        IconButton({ onConfirmValue(text) }) { Icon(Icons.Filled.Check, "Confirm") }
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
