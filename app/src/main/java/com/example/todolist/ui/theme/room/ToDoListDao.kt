package com.example.todolist.ui.theme.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ToDoListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: ToDoItem)

    @Delete
    suspend fun delete(todo: ToDoItem)

    @Query("SELECT * FROM list WHERE listId = :id")
    fun getTodoItemById(id: Int): ToDoItem

    @Transaction
    @Query("SELECT * FROM list")
    suspend fun getAllToDoList(): List<TodoAndCheckedItems>

    @Query("UPDATE list set title = :title, note = :note where listId = :id")
    suspend fun update(id: Int?, title: String?, note: String?)
}