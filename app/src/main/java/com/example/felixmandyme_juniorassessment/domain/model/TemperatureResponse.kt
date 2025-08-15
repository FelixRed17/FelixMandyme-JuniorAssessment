package com.example.felixmandyme_juniorassessment.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TemperatureResponse(
    val location: Location,
    val current: Current,
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
