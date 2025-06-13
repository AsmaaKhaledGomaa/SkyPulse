package com.asoom.skypulse.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.asoom.skypulse.presentation.component.CurrentLocation
import com.asoom.skypulse.presentation.component.CurrentWeatherInfo
import com.asoom.skypulse.presentation.component.TodayWeatherList
import com.asoom.skypulse.presentation.component.CurrentWeatherInfoList
import com.asoom.skypulse.presentation.component.DailyForecast
import com.asoom.skypulse.presentation.theme.DarkPurpleBottomColor
import com.asoom.skypulse.presentation.theme.DarkPurpleTopColor
import com.asoom.skypulse.presentation.state.WeatherUiState
import com.asoom.skypulse.presentation.theme.LightSkyBlueColor
import com.asoom.skypulse.presentation.theme.WhiteColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreenContent(weatherUiState: WeatherUiState) {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = WhiteColor
    val listState = rememberLazyListState()
    val isRow by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 2
        }
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = false
        )
    }

    when (weatherUiState) {
        WeatherUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

        is WeatherUiState.Success -> {
            val weatherForecastResponse = weatherUiState.data
            val isDay = weatherForecastResponse.currentWeather?.isDay == 1

            val backgroundGradient = if (isDay) {
                Brush.verticalGradient(
                    colors = listOf(LightSkyBlueColor, WhiteColor)
                )
            } else {
                Brush.verticalGradient(
                    colors = listOf(DarkPurpleTopColor, DarkPurpleBottomColor)
                )
            }

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = backgroundGradient)
                    .padding(horizontal = 12.dp)
                    .systemBarsPadding()
            ) {
                item { CurrentLocation(cityName = weatherUiState.cityName ?: "Unknown", isDay) }
                weatherForecastResponse.currentWeather?.let { currentWeather ->
                    item {
                        CurrentWeatherInfo(
                            currentWeather = currentWeather,
                            dailyWeather = weatherForecastResponse.daily,
                            isDay,
                            isRow
                        )
                    }
                }
                weatherForecastResponse.currentWeather?.let { currentWeather ->
                    item {
                        CurrentWeatherInfoList(
                            currentWeather = currentWeather,
                            isDay = isDay
                        )
                    }
                }
                weatherForecastResponse.hourly?.let { hourlyWeather ->
                    weatherForecastResponse.hourlyUnits?.let { hourlyUnits ->
                        item {
                            TodayWeatherList(
                                hourlyWeather = hourlyWeather,
                                hourlyUnits = hourlyUnits,
                                isDay = isDay
                            )
                        }
                    }
                }
                weatherForecastResponse.daily?.let { dailyWeather ->
                    weatherForecastResponse.dailyUnits?.let { dailyUnits ->
                        item {
                            DailyForecast(
                                dailyWeather = dailyWeather,
                                dailyWeatherUnits = dailyUnits,
                                isDay = isDay
                            )
                        }
                    }
                }
            }
        }

        is WeatherUiState.Error -> {
            Text(
                text = "Error: ${weatherUiState.message}",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}
