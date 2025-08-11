package com.example.felixmandyme_juniorassessment.data

import com.example.felixmandyme_juniorassessment.network.WeatherApi
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface WeatherRepository {
    suspend fun getWeatherInfo(): TemperatureResponse
    suspend fun getAstronomyInfo(): SunriseSunsetResponse
}

class NetworkWeatherRepository(private val weatherApi: WeatherApi): WeatherRepository{
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = formatter.format(Date())
    override suspend fun getWeatherInfo(): TemperatureResponse = weatherApi.getWeather(apiKey = "7a457d705d554f88b2d75324251706", location = "Sandton")
    override suspend fun getAstronomyInfo(): SunriseSunsetResponse = weatherApi.getAstronomy(apiKey = "7a457d705d554f88b2d75324251706", location = "Sandton", date = currentDate)
}

interface TasksRepository{
    suspend fun insertTask(tasks: Tasks)
    suspend fun updateTask(tasks: Tasks)
    suspend fun deleteTask(tasks: Tasks)

    fun getTask(id: Int): Flow<Tasks>
    fun getAllTasksCompleted(): Flow<List<Tasks>>
    fun getAllTasksIncompleted(): Flow<List<Tasks>>
    fun getAllTasksIncompleteSum(): Flow<Int>

}

class RoomTasksRepository(private val tasksDao: TasksDao): TasksRepository{
    override suspend fun insertTask(tasks: Tasks) = tasksDao.insert(tasks)

    override suspend fun updateTask(tasks: Tasks) = tasksDao.update(tasks)

    override suspend fun deleteTask(tasks: Tasks) = tasksDao.delete(tasks)

    override fun getTask(id: Int): Flow<Tasks> = tasksDao.getTask(id)

    override fun getAllTasksCompleted(): Flow<List<Tasks>> = tasksDao.getAllTasksComplete()

    override fun getAllTasksIncompleted(): Flow<List<Tasks>> = tasksDao.getAllTasksIncomplete()

    override fun getAllTasksIncompleteSum(): Flow<Int> = tasksDao.getAllTasksIncompleteCount()

}