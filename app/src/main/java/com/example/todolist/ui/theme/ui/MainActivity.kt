package com.example.todolist.ui.theme.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.ToDoListTheme
import com.example.todolist.ui.theme.viewmodel.ToDoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ToDoListViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController: NavHostController = rememberNavController()
            val isBottomBarVisible = remember { mutableStateOf(true) }
            val showArrowIcon = remember { mutableStateOf(false) }
            val showEditIcon = remember { mutableStateOf(false) }
            val isEdit = remember { mutableStateOf(false) }
            val todoItemState by viewModel.todoItemState.collectAsState()
            val scope = rememberCoroutineScope()

            ToDoListTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Scaffold(
                        topBar = {
                                 TopAppBar(
                                     title = {
                                         Text(
                                             text = "To do List",
                                             fontSize = 18.sp,
                                             modifier = Modifier.fillMaxWidth(),
                                             textAlign = TextAlign.Start
                                             )
                                     },
                                     colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                                     navigationIcon = {
                                         AnimatedVisibility(visible = showArrowIcon.value) {
                                             IconButton(onClick = {
                                                 navHostController.popBackStack()
                                             }) {
                                                 Icon(
                                                     imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                     contentDescription = null
                                                 )
                                             }
                                         }
                                     },
                                     actions = {
                                         AnimatedVisibility(visible = showEditIcon.value) {
                                             IconButton(onClick = {
                                                 isEdit.value = true
                                                 viewModel.title.value = TextFieldValue(text = todoItemState.data?.title.toString())
                                                 viewModel.note.value = TextFieldValue(text = todoItemState.data?.note.toString())
                                                 viewModel.date.value = todoItemState.data?.date.toString()
                                                 viewModel.time.value = todoItemState.data?.time.toString()
                                                 navHostController.navigate(TodoScreens.createTaskScreen)
                                             }) {
                                                 Icon(
                                                     imageVector = Icons.Filled.Create,
                                                     contentDescription = null
                                                 )
                                             }
                                         }
                                     }

                                 )
                        },
                        bottomBar = {
                            AnimatedVisibility(
                                visible = isBottomBarVisible.value,
                                enter = fadeIn(),
                                exit = fadeOut()
                            ) {
                                BottomAppBar(
                                    actions = {

                                    },
                                    floatingActionButton = {
                                        FloatingActionButton(
                                            onClick = {
                                                scope.launch {
                                                    isEdit.value = false
                                                    viewModel.clear()
                                                    navHostController.navigate(TodoScreens.createTaskScreen)
                                                }
                                            },
                                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                                        ) {
                                            Icon(Icons.Filled.Add, "Localized description")
                                        }
                                    }
                                )
                            }

                        }
                    ) {
                            Column(modifier = Modifier.padding(it)) {
                                TodoNavHost(
                                    navHostController = navHostController,
                                    showArrowIcon = showArrowIcon,
                                    isBottomBarVisible = isBottomBarVisible,
                                    showEditIcon = showEditIcon,
                                    isEdit = isEdit
                                )
                            }

                        }
                }
            }
        }
    }


    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun MainPreview() {

        ToDoListTheme {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                        },
                    ) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "icon")
                    }
                }
            ) {
                LazyColumn(modifier = Modifier.padding(it)) {

                }
            }
        }

    }
}
