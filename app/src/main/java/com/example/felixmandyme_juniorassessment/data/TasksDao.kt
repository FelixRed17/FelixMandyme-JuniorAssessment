package com.example.felixmandyme_juniorassessment.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tasks: Tasks)

    @Update
    suspend fun update(tasks: Tasks)

    @Delete
    suspend fun delete(tasks: Tasks)

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTask(id: Int): Flow<Tasks>

    @Query("SELECT * FROM tasks WHERE complete = false")
    fun getAllTasksIncomplete(): Flow<List<Tasks>>

    @Query("SELECT * FROM tasks WHERE complete = true")
    fun getAllTasksComplete(): Flow<List<Tasks>>

    @Query("SELECT COUNT(*) FROM tasks WHERE complete = false")
    fun getAllTasksIncompleteCount(): Flow<Int>

}