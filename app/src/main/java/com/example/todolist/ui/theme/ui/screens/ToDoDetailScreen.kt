package com.example.todolist.ui.theme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.ui.theme.models.Status
import com.example.todolist.ui.theme.viewmodel.ToDoListViewModel

@Composable
fun ToDoDetailScreen(viewModel: ToDoListViewModel = hiltViewModel()){

    val todoItemState by viewModel.todoItemState.collectAsState()

    LaunchedEffect(key1 = viewModel.itemId) {
       viewModel.getTodoItemById()
    }

    Box {
        when(todoItemState.status){
            Status.LOADING -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            Status.CONTENT -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = todoItemState.data?.title ?: "",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = todoItemState.data?.note ?: "",
                        fontSize = 16.sp,
                    )
                }
            }
            Status.ERROR -> {}
            null -> {}
        }

    }


}