package com.example.todolist.ui.theme.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.ui.theme.models.LCE
import com.example.todolist.ui.theme.repository.ToDoListRepository
import com.example.todolist.ui.theme.room.CheckedItem
import com.example.todolist.ui.theme.room.ToDoItem
import com.example.todolist.ui.theme.room.TodoAndCheckedItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(private val repository:ToDoListRepository):ViewModel() {

    private val _todoListState = MutableStateFlow(LCE<List<TodoAndCheckedItems>>())

    val todoListState: MutableStateFlow<LCE<List<TodoAndCheckedItems>>>
        get() = _todoListState

    private val _todoItemState = MutableStateFlow(LCE<ToDoItem>())

    private val _todoItemCheckedState = MutableStateFlow(mutableListOf(CheckedItem(checked = false, note = "")))
    val todoItemCheckedState: MutableStateFlow<MutableList<CheckedItem>>
        get() = _todoItemCheckedState

    val todoItemState: MutableStateFlow<LCE<ToDoItem>>
        get() = _todoItemState

    private var deletedTodo: ToDoItem? = null

    val note = mutableStateOf(TextFieldValue())
    val title = mutableStateOf(TextFieldValue())
    val date = mutableStateOf("")
    val time = mutableStateOf("")
    var itemId = 0

    val notesMap = mutableStateMapOf<Int, Boolean>()

    fun addCheckedItem(item: CheckedItem) {
        _todoItemCheckedState.value = (_todoItemCheckedState.value + item).toMutableList()
    }
    fun removeCheckedItem(item: CheckedItem) {
        _todoItemCheckedState.value = (_todoItemCheckedState.value - item).toMutableList()
    }

    fun isChecked(id: Int, isChecked: Boolean) {
        _todoItemCheckedState.value.find { it.todoId == id }?.checked = isChecked
        if (isChecked) {
            notesMap[id] = true
        } else {
            notesMap.remove(id)
        }

    }
    fun updateItem(index: Int, newItem: CheckedItem) {
        val updatedList = _todoItemCheckedState.value.toMutableList()
        updatedList[index] = newItem
        _todoItemCheckedState.value = updatedList
    }

    fun insertTodo() {

        val todo = ToDoItem(
            title = title.value.text,
            note = note.value.text,
            date = date.value,
            time = time.value
        )
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(todo)
        }
    }

    fun updateTodo() {
        val todo = ToDoItem(
            listId = itemId,
            title = title.value.text,
            note = note.value.text,
            date = date.value,
            time = time.value
        )
        viewModelScope.launch(Dispatchers.IO){
                repository.update(todo)
        }
    }

    fun clear(){
        title.value = TextFieldValue("")
        note.value = TextFieldValue("")
        date.value = ""
        time.value = ""
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

//    fun addNewCheckedItem(todoIndex: Int) {
//        val updatedList = _todoItemCheckedState.value.toMutableList()
//        val todo = updatedList[todoIndex].todo
//        val newCheckedItem = CheckedItem(todoId = todo.listId, checked = false, title = "", note = "")
//        updatedList[todoIndex] = updatedList[todoIndex].copy(
//            checkedItems = updatedList[todoIndex].checkedItems + newCheckedItem
//        )
//        _todoItemCheckedState.value = updatedList
//    }
//
//    fun updateCheckedItem(todoIndex: Int, checkedIndex: Int, newCheckedItem: CheckedItem) {
//        val updatedList = _todoItemCheckedState.value.toMutableList()
//        val checkedItems = updatedList[todoIndex].checkedItems.toMutableList()
//        checkedItems[checkedIndex] = newCheckedItem
//        updatedList[todoIndex] = updatedList[todoIndex].copy(checkedItems = checkedItems)
//        _todoItemCheckedState.value = updatedList
//    }
}