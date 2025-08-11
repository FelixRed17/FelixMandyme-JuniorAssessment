package com.example.felixmandyme_juniorassessment.data

import kotlinx.serialization.Serializable

@Serializable
data class SunriseSunsetResponse(
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