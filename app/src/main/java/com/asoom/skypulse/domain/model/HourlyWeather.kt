package com.asoom.skypulse.domain.model

data class HourlyWeather(
    val time: List<String>,
    val temperature: List<Double>,
    val weatherCode: List<Int>
)
