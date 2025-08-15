package com.example.felixmandyme_juniorassessment.domain.repository

import com.example.felixmandyme_juniorassessment.domain.model.Tasks
import kotlinx.coroutines.flow.Flow

interface RoomDatabaseRepository{
    suspend fun insertTask(tasks: Tasks)
    suspend fun updateTask(tasks: Tasks)
    suspend fun deleteTask(tasks: Tasks)

    fun getTask(id: Int): Flow<Tasks>
    fun getAllTasksCompleted(): Flow<List<Tasks>>
    fun getAllTasksIncompleted(): Flow<List<Tasks>>
    fun getAllTasksIncompleteSum(): Flow<Int>

}

