package com.example.felixmandyme_juniorassessment.data.local

import com.example.felixmandyme_juniorassessment.domain.model.Tasks
import com.example.felixmandyme_juniorassessment.domain.model.TasksDao
import com.example.felixmandyme_juniorassessment.domain.repository.RoomDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomDatabaseRepositoryImpl @Inject constructor(private val tasksDao: TasksDao):
    RoomDatabaseRepository {
    override suspend fun insertTask(tasks: Tasks) = tasksDao.insert(tasks)

    override suspend fun updateTask(tasks: Tasks) = tasksDao.update(tasks)

    override suspend fun deleteTask(tasks: Tasks) = tasksDao.delete(tasks)

    override fun getTask(id: Int): Flow<Tasks> = tasksDao.getTask(id)

    override fun getAllTasksCompleted(): Flow<List<Tasks>> = tasksDao.getAllTasksComplete()

    override fun getAllTasksIncompleted(): Flow<List<Tasks>> = tasksDao.getAllTasksIncomplete()

    override fun getAllTasksIncompleteSum(): Flow<Int> = tasksDao.getAllTasksIncompleteCount()

}