package com.example.felixmandyme_juniorassessment.data

import com.example.felixmandyme_juniorassessment.network.WeatherApi
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherInfo(): WeatherInfo
    suspend fun getAstronomyInfo(): AstronomyInfo
}

class NetworkWeatherRepository(private val weatherApi: WeatherApi): WeatherRepository{
    override suspend fun getWeatherInfo(): WeatherInfo = weatherApi.getWeather(apiKey = "7a457d705d554f88b2d75324251706", location = "Sandton")
    override suspend fun getAstronomyInfo(): AstronomyInfo = weatherApi.getAstronomy(apiKey = "7a457d705d554f88b2d75324251706", location = "Sandton", date = "2025-06-17")
}

interface TasksRepository{
    suspend fun insertTask(tasks: Tasks)
    suspend fun updateTask(tasks: Tasks)
    suspend fun deleteTask(tasks: Tasks)

    fun getTask(id: Int): Flow<Tasks>
    fun getAllTasksCompleted(): Flow<List<Tasks>>
    fun getAllTasksIncompleted(): Flow<List<Tasks>>

}

class RoomTasksRepository(private val tasksDao: TasksDao): TasksRepository{
    override suspend fun insertTask(tasks: Tasks) = tasksDao.insert(tasks)

    override suspend fun updateTask(tasks: Tasks) = tasksDao.update(tasks)

    override suspend fun deleteTask(tasks: Tasks) = tasksDao.delete(tasks)

    override fun getTask(id: Int): Flow<Tasks> = tasksDao.getTask(id)

    override fun getAllTasksCompleted(): Flow<List<Tasks>> = tasksDao.getAllTasksComplete()

    override fun getAllTasksIncompleted(): Flow<List<Tasks>> = tasksDao.getAllTasksIncomplete()

}