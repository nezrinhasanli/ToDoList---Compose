package com.example.todolist.ui.theme.repository

import com.example.todolist.ui.theme.room.ToDoItem
import com.example.todolist.ui.theme.room.ToDoListDao
import com.example.todolist.ui.theme.room.TodoAndCheckedItems
import javax.inject.Inject

class ToDoListRepository @Inject constructor(private val toDoDao:ToDoListDao) {

    suspend fun insert(todo: ToDoItem) = toDoDao.insert(todo)


    suspend fun delete(todo: ToDoItem) = toDoDao.delete(todo)


    suspend fun update(todo: ToDoItem) = toDoDao.update(todo.listId, todo.title, todo.note)

    fun getTodoItemById(id: Int) = toDoDao.getTodoItemById(id)

    suspend fun getAllToDoList(): List<TodoAndCheckedItems> = toDoDao.getAllToDoList()

}