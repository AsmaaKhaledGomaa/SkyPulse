package com.asoom.skypulse.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherDto(
    @SerialName("time")
    val time: List<String>,
    @SerialName("weather_code")
    val weatherCode: List<Int>,
    @SerialName("temperature_2m_max")
    val maxTemp: List<Double>,
    @SerialName("temperature_2m_min")
    val minTemp: List<Double>
)