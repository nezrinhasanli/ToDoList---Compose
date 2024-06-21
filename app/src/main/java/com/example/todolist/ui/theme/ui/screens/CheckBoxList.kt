package com.example.todolist.ui.theme.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.ui.theme.ui.components.CheckBoxTextField
import com.example.todolist.ui.theme.ui.components.TaskOutlinedTextField
import com.example.todolist.ui.theme.viewmodel.ToDoListViewModel

@Composable
fun CheckBoxList(
    viewModel: ToDoListViewModel = hiltViewModel(),
    ){
    val (checkedState, onStateChange) = remember { mutableStateOf(false) }

    Column {
        TaskOutlinedTextField(
            label = "Title",
            value = viewModel.title.value,
            onValueChange = {
                viewModel.title.value = it
            })

        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .toggleable(
                    value = checkedState,
                    onValueChange = { onStateChange(!checkedState) },
                    role = Role.Checkbox
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = null
            )

            CheckBoxTextField(
                value = viewModel.note.value,
                onValueChange = {
                    viewModel.note.value = it
                }
                )
        }

    }

}