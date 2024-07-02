package com.example.todolist.ui.theme.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todolist.ui.theme.room.CheckedItem
import com.example.todolist.ui.theme.ui.TodoScreens
import com.example.todolist.ui.theme.ui.components.CheckBoxTextField
import com.example.todolist.ui.theme.ui.components.TaskOutlinedTextField
import com.example.todolist.ui.theme.viewmodel.ToDoListViewModel

@Composable
fun CheckBoxList(
    viewModel: ToDoListViewModel,
    isEdit: Boolean = false,
    navController: NavHostController
    ){
    val checkedItemList by viewModel.todoItemCheckedState.collectAsState()
    val focusRequesters = remember { MutableList(checkedItemList.size) { FocusRequester() } }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 16.dp)) {

        TaskOutlinedTextField(
            label = "Title",
            placeHolder = "Enter title here",
            value = viewModel.title.value,
            onValueChange = {
                viewModel.title.value = it
            })

        LazyColumn(modifier = Modifier.weight(1f)) {

            itemsIndexed(checkedItemList) {index, item ->

//                val nextFocusRequester = if (index < checkedItemList.size - 1) {
//                    focusRequesters[index + 1]
//                } else {
//                    FocusRequester()
//                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.checked ?: false,
                        onCheckedChange = { checked ->
//                            viewModel.isChecked(item.todoId ?: 0,checked)
                        }
                    )

                        CheckBoxTextField(
                            value = viewModel.note.value,
                            onValueChange = { note ->
//                                viewModel.note.value = note
                            },
                           viewModel = viewModel
                        )
                }
            }
            }


        Button(
            onClick = {
//                if (isEdit){
//                    viewModel.updateTodo()
//                }
//                else{
//                    viewModel.insertTodo()
//                }
                navController.navigate(TodoScreens.todoListScreen)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = if(!isEdit) "Create" else "Update"
            )
}
        }

    }

