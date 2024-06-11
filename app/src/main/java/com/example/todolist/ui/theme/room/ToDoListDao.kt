package com.example.todolist.ui.theme.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: ToDoItem)

    @Delete
    suspend fun delete(todo: ToDoItem)

    @Query("SELECT * FROM list WHERE id = :id")
    fun getTodoItemById(id: Int): List<ToDoItem>

    @Query("SELECT * FROM list")
    suspend fun getAllToDoList(): List<ToDoItem>

    @Query("UPDATE list set title = :title, note = :note where id = :id")
    suspend fun update(id: Int?, title: String?, note: String?)
}