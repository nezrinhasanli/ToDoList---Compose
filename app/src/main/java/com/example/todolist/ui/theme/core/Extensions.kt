package com.example.todolist.ui.theme.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toTimeDateString(): String {
    val dateTime = Date(this)
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return format.format(dateTime)
}
