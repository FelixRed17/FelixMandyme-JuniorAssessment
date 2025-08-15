package com.example.felixmandyme_juniorassessment.data

import com.example.felixmandyme_juniorassessment.API_KEY
import com.example.felixmandyme_juniorassessment.network.WeatherApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

interface WeatherRepository {
    suspend fun getWeatherInfo(): TemperatureResponse
    suspend fun getAstronomyInfo(): SunriseSunsetResponse
}

class NetworkWeatherRepository @Inject constructor(private val weatherApi: WeatherApi): WeatherRepository{
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = formatter.format(Date())
    override suspend fun getWeatherInfo(): TemperatureResponse = weatherApi.getWeather(apiKey = API_KEY, location = "Sandton")
    override suspend fun getAstronomyInfo(): SunriseSunsetResponse = weatherApi.getAstronomy(apiKey = API_KEY, location = "Sandton", date = currentDate)
}
