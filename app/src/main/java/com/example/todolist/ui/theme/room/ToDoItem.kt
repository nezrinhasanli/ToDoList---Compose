package com.example.todolist.ui.theme.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "list")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true) var listId: Int? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "note") val note: String? = null,
    @ColumnInfo(name = "date") val date: String? = null,
    @ColumnInfo(name = "time") val time: String? = null,
    @ColumnInfo(name = "createdAt") val createdAt: String? = null,
    @ColumnInfo(name = "updatedAt") val updatedAt: String? = null
)

@Entity
data class CheckedItem(
    @PrimaryKey(autoGenerate = true) var checkedId: Int? = null,
    @ColumnInfo(name = "todoId") val todoId: Int? = null,
    @ColumnInfo(name = "checked") var checked: Boolean? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "note") val note: String? = null,
)
data class TodoAndCheckedItems(
    @Embedded val todo: ToDoItem,
    @Relation(
        parentColumn = "listId",
        entityColumn = "todoId"
    )
    val checkedItems: List<CheckedItem>
)
