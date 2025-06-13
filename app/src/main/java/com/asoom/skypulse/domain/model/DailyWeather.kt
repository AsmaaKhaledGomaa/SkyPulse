package com.asoom.skypulse.domain.model

data class DailyWeather(
    val time: List<String>,
    val weatherCode: List<Int>,
    val maxTemp: List<Double>,
    val minTemp: List<Double>
)
