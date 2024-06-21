package com.example.todolist.ui.theme.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.core.toTimeDateString
import com.example.todolist.ui.theme.ui.TodoScreens
import com.example.todolist.ui.theme.ui.components.PickerBox
import com.example.todolist.ui.theme.ui.components.TaskOutlinedTextField
import com.example.todolist.ui.theme.ui.components.TimePickerDialog
import com.example.todolist.ui.theme.viewmodel.ToDoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewTask(
    viewModel: ToDoListViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    isEdit: Boolean = false
    ){

    var showDatePickerDialog by remember { mutableStateOf(false) }
    var showTimePickerDialog by remember { mutableStateOf(false) }
    var toggleIsChecked by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()

    LaunchedEffect(key1 = viewModel.itemId) {
        viewModel.getTodoItemById()
    }

    if(showDatePickerDialog){
        DatePickerDialog(
            onDismissRequest = {showDatePickerDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.date.value = datePickerState.selectedDateMillis!!.toTimeDateString()
                        showDatePickerDialog = false
                              },
                    enabled = datePickerState.selectedDateMillis != null
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePickerDialog = false }) {
                    Text(text = "Dismiss")
                }
            },
            content = { DatePicker(state = datePickerState) }
        )
    }

    if (showTimePickerDialog) {

       TimePickerDialog(
           onDismissRequest = { showTimePickerDialog = false },
           confirmButton = {
               TextButton(
                   onClick = {
                       viewModel.time.value = timePickerState.hour.toString() + ":" + timePickerState.minute.toString()
                       showTimePickerDialog = false
                   }
               ) { Text("Confirm") }
                           },
           dismissButton = {
               TextButton(onClick = { showTimePickerDialog = false }) {
                   Text(text = "Dismiss")
               }
           }
       ) {
           TimePicker(state = timePickerState)
           Log.d("Time", timePickerState.hour.toString() + ":" + timePickerState.minute.toString())
       }

    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Title",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
        TaskOutlinedTextField(
            label = "Enter title here",
            value = viewModel.title.value,
            onValueChange = {
                viewModel.title.value = it
            })
        Text(
            text = "Note",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 24.dp)

            )
        TaskOutlinedTextField(
            label = "Enter task here",
            value = viewModel.note.value,
            onValueChange = {
                viewModel.note.value = it
            })

        Row(
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp),
        ) {
            Text(
                text = "Date is enabled",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .weight(1f),
                fontSize = 17.sp
                )
            Switch(
                checked = toggleIsChecked,
                onCheckedChange = {
                    toggleIsChecked = it
                },
            )
        }

        if(toggleIsChecked){
            Text(
                text = "Date",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)

            )
            PickerBox(
                onClick = {
                    showDatePickerDialog = true
                },
                label = "Enter date here",
                value = viewModel.date.value
            )

            Text(
                text = "Time",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
            )

            PickerBox(
                onClick = {
                    showTimePickerDialog = true
                },
                label = "Enter time here",
                value = viewModel.time.value
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                if (isEdit){
                    viewModel.updateTodo()
                }
                else{
                    viewModel.insertTodo()
                }
                navController.navigate(TodoScreens.todoListScreen)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = if(!isEdit) "Create" else "Update"
            )
        }
    }
}


