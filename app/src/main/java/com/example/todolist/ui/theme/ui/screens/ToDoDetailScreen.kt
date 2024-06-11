package com.example.todolist.ui.theme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.ui.theme.viewmodel.ToDoListViewModel

@Composable
fun ToDoDetailScreen(viewModel: ToDoListViewModel = hiltViewModel()){

    LaunchedEffect(key1 = true) {
       viewModel.getTodoItemById()
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Text(
            text = viewModel.title.value.text,
            fontSize = 16.sp,
            )
        Text(
            text = viewModel.note.value.text,
            fontSize = 16.sp,
            )
    }

}