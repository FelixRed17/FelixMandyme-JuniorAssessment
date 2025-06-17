package com.example.felixmandyme_juniorassessment.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.felixmandyme_juniorassessment.network.WeatherApi
import retrofit2.Response
import java.time.LocalDateTime

interface WeatherRepository {
    suspend fun getWeatherInfo(): WeatherInfo
    suspend fun getAstronomyInfo(): AstronomyInfo
}

class NetworkWeatherRepository(private val weatherApi: WeatherApi): WeatherRepository{

    override suspend fun getWeatherInfo(): WeatherInfo = weatherApi.getWeather(apiKey = "7a457d705d554f88b2d75324251706", location = "Sandton")
    override suspend fun getAstronomyInfo(): AstronomyInfo = weatherApi.getAstronomy(apiKey = "7a457d705d554f88b2d75324251706", location = "Sandton", date = "2025-06-17")
}