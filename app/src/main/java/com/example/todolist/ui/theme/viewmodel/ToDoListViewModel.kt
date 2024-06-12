package com.example.todolist.ui.theme.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.ui.theme.models.LCE
import com.example.todolist.ui.theme.repository.ToDoListRepository
import com.example.todolist.ui.theme.room.ToDoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(private val repository:ToDoListRepository):ViewModel() {

    private val _todoListState = MutableStateFlow(LCE<List<ToDoItem>>())

    val todoListState: MutableStateFlow<LCE<List<ToDoItem>>>
        get() = _todoListState

    private val _todoItemState = MutableStateFlow(LCE<ToDoItem>())

    val todoItemState: MutableStateFlow<LCE<ToDoItem>>
        get() = _todoItemState

    private var deletedTodo: ToDoItem? = null

    val note = mutableStateOf(TextFieldValue())
    val title = mutableStateOf(TextFieldValue())
    val date = mutableStateOf("")
    var itemId = 0


    fun insertTodo() {

        val todo = ToDoItem(
            title = title.value.text,
            note = note.value.text,
            date = date.value
        )
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(todo)
        }
    }

    fun updateTodo(todo: ToDoItem) {
        _todoListState.value = LCE.loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                repository.update(todo)
                _todoListState.value = LCE.content(listOf(todo))

            }
            catch(e: Exception) {
                _todoListState.value = LCE.error()
            }
        }
    }

    fun deleteTodo(todo: ToDoItem) {

        viewModelScope.launch(Dispatchers.IO){
            repository.delete(todo)
        }
    }

    fun undoDeletedTodo() {
        deletedTodo.let { todo ->
            viewModelScope.launch(Dispatchers.IO){
                repository.insert(todo!!)
            }
        }
    }

    fun getAllToDoList() {
        _todoListState.value = LCE.loading()
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllToDoList().also {
                try {
                    val list = repository.getAllToDoList()
                    _todoListState.value = LCE.content(list)
                } catch(e: Exception) {
                    e.printStackTrace()
                    _todoListState.value = LCE.error()
                }
            }
        }
    }

    fun getTodoItemById(){
        _todoItemState.value = LCE.loading()
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllToDoList().also {
                try {
                    val item = repository.getTodoItemById(itemId)
                    _todoItemState.value = LCE.content(item)
                } catch(e: Exception) {
                    e.printStackTrace()
                    _todoItemState.value = LCE.error()
                }
            }
        }
    }

//    fun updateTask(newValue: String){
//        todoItemState.value = todoItemState.value.data.copy
//    }
}