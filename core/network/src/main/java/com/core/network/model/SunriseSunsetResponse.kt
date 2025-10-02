package com.core.network.model


data class SunriseSunsetResponse(
    val location: Location,
    val astronomy: AstronomyDetail
)


data class AstronomyDetail(
    val astro: Astronomy
)


data class Astronomy(
    val sunrise: String,
    val sunset: String
)