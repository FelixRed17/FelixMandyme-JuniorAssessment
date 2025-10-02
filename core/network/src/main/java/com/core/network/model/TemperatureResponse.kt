package com.core.network.model

import kotlinx.serialization.SerialName

data class TemperatureResponse(
    val location: Location,
    val current: Current,
)


data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String
)


data class Current(
    @SerialName("temp_c") val temp: Double
)