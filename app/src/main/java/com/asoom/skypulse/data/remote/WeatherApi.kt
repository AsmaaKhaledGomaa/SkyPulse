package com.asoom.skypulse.data.remote

import com.asoom.skypulse.data.utils.BASE_URL
import com.asoom.skypulse.data.remote.dto.WeatherForecastResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherApi(private val httpClient: HttpClient) {
    suspend fun getWeatherForecast(
        latitude: Double, longitude: Double
    ): WeatherForecastResponseDto = httpClient.get(BASE_URL) {
        parameter("latitude", latitude)
        parameter("longitude", longitude)
        parameter(
            "current",
            "temperature_2m,apparent_temperature,is_day,relative_humidity_2m,wind_speed_10m," +
                    "precipitation_probability,weather_code,surface_pressure,uv_index,pressure_msl,rain"
        )
        parameter("hourly", "temperature_2m,weather_code")
        parameter("daily", "weather_code,temperature_2m_max,temperature_2m_min")
        parameter("timezone", "auto")
    }.body()
}
