package com.example.todolist.ui.theme.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoItem::class, CheckedItem::class], version = 1, exportSchema = true)
abstract class ToDoListDataBase: RoomDatabase() {
    abstract fun getTodoDao(): ToDoListDao
}