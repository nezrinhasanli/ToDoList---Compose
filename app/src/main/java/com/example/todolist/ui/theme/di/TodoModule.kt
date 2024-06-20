package com.example.todolist.ui.theme.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

//    val MIGRATION_3_4 = object : Migration(3, 4) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//
//            database.execSQL("ALTER TABLE list ADD COLUMN time VARCHAR(255)")
//        }
//    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context):
            ToDoListDataBase {
        return Room.databaseBuilder(
            appContext,
            ToDoListDataBase::class.java,
            "todo_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: ToDoListDao): ToDoListRepository = ToDoListRepository(dao)
}