package com.asoom.skypulse.data.remote.dto

import com.asoom.skypulse.domain.model.HourlyWeatherUnits
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherUnitsDto(
    @SerialName("time")
    val time: String,
    @SerialName("temperature_2m")
    val temperature: String,
    @SerialName("weather_code")
    val weatherCode: String
)
