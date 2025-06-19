package com.example.felixmandyme_juniorassessment.data

import android.content.Context
import android.util.Log
import com.example.felixmandyme_juniorassessment.network.WeatherApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val weatherRepository: WeatherRepository
    val tasksRepository: TasksRepository
}

class DefaultAppContainer( private val context: Context): AppContainer{
    private val BASE_URL = "https://api.weatherapi.com/"

    private val json = Json {
        ignoreUnknownKeys = true // avoids crashing on unexpected fields
    }



    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    override val weatherRepository: WeatherRepository by lazy {
        NetworkWeatherRepository(retrofitService)
    }

    override val tasksRepository: TasksRepository by lazy {
        RoomTasksRepository(TasksDatabase.getDatabase(context).tasksDao())
    }

}