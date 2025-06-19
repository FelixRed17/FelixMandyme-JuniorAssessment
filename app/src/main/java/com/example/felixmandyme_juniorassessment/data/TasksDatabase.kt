package com.example.felixmandyme_juniorassessment.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tasks::class], version = 1, exportSchema = false)
abstract class TasksDatabase: RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object {
        @Volatile
        private var Instance: TasksDatabase? = null

        fun getDatabase(context: Context): TasksDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TasksDatabase::class.java, "task_database")
                    .build()
                    .also { Instance = it }
            }

        }

    }
}