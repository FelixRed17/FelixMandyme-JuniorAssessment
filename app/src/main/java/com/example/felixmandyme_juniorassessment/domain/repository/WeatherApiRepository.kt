package com.example.felixmandyme_juniorassessment.domain.repository

import com.example.felixmandyme_juniorassessment.domain.model.SunriseSunsetResponse
import com.example.felixmandyme_juniorassessment.domain.model.TemperatureResponse

interface WeatherRepository {
    suspend fun getWeatherInfo(): TemperatureResponse
    suspend fun getAstronomyInfo(): SunriseSunsetResponse
}


