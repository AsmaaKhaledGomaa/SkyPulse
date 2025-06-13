package com.asoom.skypulse.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asoom.skypulse.R
import com.asoom.skypulse.domain.model.DailyWeather
import com.asoom.skypulse.domain.model.DailyWeatherUnits
import com.asoom.skypulse.domain.util.WeatherType
import com.asoom.skypulse.presentation.theme.DarkPurpleAlpha8Color
import com.asoom.skypulse.presentation.theme.DarkPurpleBottomColor
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha70Color
import com.asoom.skypulse.presentation.theme.WhiteColorAlpha8Color
import com.asoom.skypulse.presentation.theme.WhiteColor
import com.asoom.skypulse.presentation.theme.Urbanist
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecast(
    dailyWeather: DailyWeather,
    dailyWeatherUnits: DailyWeatherUnits,
    isDay: Boolean
) {
    Text(
        modifier = Modifier.padding(bottom = 12.dp, start = 12.dp),
        text = "Next 7 days",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = Urbanist,
            fontWeight = FontWeight.SemiBold,
            color = if (isDay) DarkPurpleBottomColor else WhiteColor,
        )
    )

    val daysOfWeek = dailyWeather.time.map { java.time.LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("EEEE")) }
    val maxTemps = dailyWeather.maxTemp.map { "${it.toInt()}°C" }
    val minTemps = dailyWeather.minTemp.map { "${it.toInt()}°C" }
    val weatherIcons = dailyWeather.weatherCode.map { WeatherType.fromWMO(it).iconResDay }

    if (dailyWeather.time.isEmpty() || dailyWeather.weatherCode.isEmpty()) {
        Text(
            text = "No daily forecast available",
            color = if (isDay) DarkPurpleBottomColor else WhiteColor,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
            ),
            modifier = Modifier.padding(top = 12.dp, start = 12.dp, bottom = 32.dp)
        )
    } else {
        DailyForecastList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .background(
                    color = if (isDay) WhiteColorAlpha70Color else DarkPurpleAlpha8Color,
                    shape = RoundedCornerShape(24.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isDay) DarkPurpleAlpha8Color else WhiteColorAlpha8Color,
                    shape = RoundedCornerShape(24.dp)
                ),
            days = daysOfWeek,
            maxTemperaturesOfDay = maxTemps,
            minimumTemperaturesOfDay = minTemps,
            weatherImages = weatherIcons,
            isDay = isDay,
        )
    }
}

