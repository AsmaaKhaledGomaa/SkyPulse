package com.asoom.skypulse.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asoom.skypulse.domain.model.HourlyWeather
import com.asoom.skypulse.domain.model.HourlyWeatherUnits
import com.asoom.skypulse.domain.util.WeatherType
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha70Color
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha8Color
import com.asoom.skypulse.presentation.theme.DarkPurpleBottomColor
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha70Color
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha8Color
import com.asoom.skypulse.presentation.theme.WhiteColor
import com.asoom.skypulse.presentation.theme.Urbanist
import androidx.compose.foundation.lazy.items
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodayWeatherList(
    hourlyWeather: HourlyWeather,
    hourlyUnits: HourlyWeatherUnits,
    isDay: Boolean
) {
    Text(
        modifier = Modifier.padding(start = 12.dp, top = 24.dp),
        text = "Today",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = Urbanist,
            fontWeight = FontWeight.SemiBold,
            color = if (isDay) DarkPurpleBottomColor else WhiteColor,
        )
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (hourlyWeather.time.isEmpty()) {
            item {
                Text(
                    text = "No hourly data available",
                    color = if (isDay) DarkPurpleBottomColor else WhiteColor,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.Medium,
                    ),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        } else {
            items(hourlyWeather.time.take(24)) { time ->
                val index = hourlyWeather.time.indexOf(time)
                val weatherCode = hourlyWeather.weatherCode.getOrNull(index) ?: 0
                val temperature = hourlyWeather.temperature.getOrNull(index) ?: 0.0
                val weatherType = WeatherType.fromWMO(weatherCode)

                val formatter = DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd")
                    .optionalStart()
                    .appendLiteral('T')
                    .appendPattern("HH:mm")
                    .optionalStart()
                    .appendLiteral(":ss")
                    .optionalEnd()
                    .optionalEnd()
                    .toFormatter()

                val dateTime = try {
                    LocalDateTime.parse(time, formatter)
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Fallback to current time if parsing fails completely, to prevent crash
                    LocalDateTime.now()
                }
                val formattedTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))

                TodayWeather(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = if (isDay) DarkPurpleAlpha8Color else WhiteColorAlpha8Color,
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .width(88.dp)
                        .height(120.dp)
                        .background(
                            color = if (isDay) WhiteColorAlpha70Color else DarkPurpleAlpha70Color,
                            shape = RoundedCornerShape(size = 20.dp)
                        ),
                    weatherImage = painterResource(id = if (isDay) weatherType.iconResDay else weatherType.iconResNight),
                    weatherTemperature = "${temperature.toInt()}°C",
                    currentHour = formattedTime,
                    isDay = if (isDay) 1 else 0,
                )
            }
        }
    }
}

