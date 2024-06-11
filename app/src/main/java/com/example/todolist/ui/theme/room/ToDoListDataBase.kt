package com.example.todolist.ui.theme.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoItem::class], version = 3, exportSchema = false)
abstract class ToDoListDataBase: RoomDatabase() {
    abstract fun getTodoDao(): ToDoListDao
}