package com.example.todolist.ui.theme.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.models.Status
import com.example.todolist.ui.theme.ui.TodoScreens
import com.example.todolist.ui.theme.viewmodel.ToDoListViewModel

@Composable
fun ToDoListScreen(
    viewModel: ToDoListViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val todoItemsState by viewModel.todoItemState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getAllToDoList()
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        when (todoItemsState.status) {

            null, Status.LOADING -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            Status.CONTENT -> {

                LazyColumn {
                    itemsIndexed(todoItemsState.data ?: emptyList()) { index, item ->

                        ToDoListBox(
                            title = item.title ?: "empty",
                            date = item.date ?: "empty",
                            onItemClick = {
                                item.id = viewModel.itemId
                                navController.navigate(TodoScreens.toDoDetailScreen)
                            }
                        )


                    }

                }
            }

            Status.ERROR -> {

            }

        }
    }
}

@Composable
fun ToDoListBox(
    title: String,
    date: String,
    onItemClick: () -> Unit = {}
    ) {

    val shape = RoundedCornerShape(12.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 16.dp)
            .background(color = MaterialTheme.colorScheme.background, shape = shape)
            .border(width = 1.dp, color = Color.Gray, shape = shape)
            .clip(shape = shape)
            .clickable { onItemClick() }
    ) {
        Column {
            Text(
                text = title,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
                )
            Text(
                text = date,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

        }
    }

}