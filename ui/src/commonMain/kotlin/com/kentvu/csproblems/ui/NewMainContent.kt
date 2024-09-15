package com.kentvu.csproblems.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kentvu.csproblems.components.MainComponent
import com.kentvu.csproblems.components.MainComponent.Event
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
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
    ) {
    }
  }
}
