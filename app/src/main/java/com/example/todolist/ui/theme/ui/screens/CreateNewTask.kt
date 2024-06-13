package com.example.todolist.ui.theme.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.core.toTimeDateString
import com.example.todolist.ui.theme.ui.TodoScreens
import com.example.todolist.ui.theme.ui.components.DatePickerBox
import com.example.todolist.ui.theme.ui.components.TextFieldInput
import com.example.todolist.ui.theme.viewmodel.ToDoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewTask(
    viewModel: ToDoListViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    isEdit: Boolean = false
    ){
    val datePickerState = rememberDatePickerState()
    var showDatePickerDialog by remember { mutableStateOf(false) }

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
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Title",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
        TextFieldInput(
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
        TextFieldInput(
            label = "Enter task here",
            value = viewModel.note.value,
            onValueChange = {
                viewModel.note.value = it
            })
        Text(
            text = "Date",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)

        )
        DatePickerBox(
            onClick = {
                showDatePickerDialog = true
            },
            label = "Enter date here",
            value = viewModel.date.value
        )
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


