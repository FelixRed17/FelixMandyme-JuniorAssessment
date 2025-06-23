package com.example.felixmandyme_juniorassessment.network

import com.example.felixmandyme_juniorassessment.data.AstronomyInfo
import com.example.felixmandyme_juniorassessment.data.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String
    ):WeatherInfo

    @GET("/v1/astronomy.json")
    suspend fun getAstronomy(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("dt") date: String
    ): AstronomyInfo
}