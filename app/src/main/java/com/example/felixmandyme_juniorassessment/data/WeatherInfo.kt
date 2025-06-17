package com.example.felixmandyme_juniorassessment.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfo(
    val location: Location,
    val current: Current,
)
@Serializable
data class AstronomyInfo(
    val location: Location,
    val astronomy: AstronomyDetail
)

@Serializable
data class AstronomyDetail(
    val astro: Astronomy
)

@Serializable
data class Astronomy(
    val sunrise: String,
    val sunset: String
)

@Serializable
data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String
)

@Serializable
data class Current(
    @SerialName("temp_c") val temp: Double
)
