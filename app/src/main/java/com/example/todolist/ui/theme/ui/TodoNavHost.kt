package com.example.todolist.ui.theme.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolist.ui.theme.ui.screens.CheckBoxList
import com.example.todolist.ui.theme.ui.screens.CreateNewTask
import com.example.todolist.ui.theme.ui.screens.ToDoDetailScreen
import com.example.todolist.ui.theme.ui.screens.ToDoListScreen
import com.example.todolist.ui.theme.viewmodel.ToDoListViewModel

@Composable
fun TodoNavHost(
    navHostController: NavHostController,
    viewModel: ToDoListViewModel = hiltViewModel(),
    showArrowIcon: MutableState<Boolean>,
    showEditIcon: MutableState<Boolean>,
    isBottomBarVisible: MutableState<Boolean>,
    isEdit: MutableState<Boolean>
) {
    NavHost(
        navController = navHostController,
        startDestination = TodoScreens.todoListScreen
    ) {
        composable(TodoScreens.todoListScreen) {
            ToDoListScreen(viewModel = viewModel, navController = navHostController)
            showArrowIcon.value = false
            showEditIcon.value = false
            isBottomBarVisible.value = true
        }
        composable(TodoScreens.createTaskScreen) {
            CreateNewTask(viewModel = viewModel,navController = navHostController, isEdit = isEdit.value)
            showArrowIcon.value = true
            showEditIcon.value = false
            isBottomBarVisible.value = false
        }

        composable(TodoScreens.toDoDetailScreen) {
            ToDoDetailScreen(viewModel = viewModel)
            showArrowIcon.value = true
            showEditIcon.value = true
            isBottomBarVisible.value = false
        }
        composable(TodoScreens.checkBoxScreen) {
            CheckBoxList(viewModel = viewModel)
            showArrowIcon.value = true
            showEditIcon.value = false
            isBottomBarVisible.value = false
        }

    }
}

object TodoScreens {
    const val todoListScreen = "todoListScreen"
    const val createTaskScreen = "createTaskScreen"
    const val toDoDetailScreen = "toDoDetailScreen"
    const val checkBoxScreen = "checkBoxScreen"
}

