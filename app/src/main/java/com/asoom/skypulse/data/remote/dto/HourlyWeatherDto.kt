package com.asoom.skypulse.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDto(
    @SerialName("time")
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperature: List<Double>,
    @SerialName("weather_code")
    val weatherCode: List<Int>
)
