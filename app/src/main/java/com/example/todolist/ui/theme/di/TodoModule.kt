package com.example.todolist.ui.theme.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.ui.theme.repository.ToDoListRepository
import com.example.todolist.ui.theme.room.ToDoListDao
import com.example.todolist.ui.theme.room.ToDoListDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TodoModule {

    @InstallIn(SingletonComponent::class)
    @Module
    class DatabaseModule {
        @Provides
        fun provideChannelDao(todoDatabase: ToDoListDataBase): ToDoListDao {
            return todoDatabase.getTodoDao()
        }
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context):
            ToDoListDataBase {
        return Room.databaseBuilder(
            appContext,
            ToDoListDataBase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: ToDoListDao): ToDoListRepository = ToDoListRepository(dao)
}