package com.asoom.skypulse.presentation.viewModel

import com.asoom.skypulse.presentation.state.WeatherUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asoom.skypulse.data.DataState
import com.asoom.skypulse.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val weatherState: StateFlow<WeatherUiState> = _weatherState

    fun getWeather(latitude: Double, longitude: Double, cityName: String) {
        viewModelScope.launch {
            getWeatherUseCase
                .getWeatherForecast(latitude, longitude)
                .collectLatest { dataState ->
                    _weatherState.value = when (dataState) {
                        is DataState.Loading -> WeatherUiState.Loading
                        is DataState.Success -> WeatherUiState.Success(dataState.data, cityName)
                        is DataState.Error -> WeatherUiState.Error(dataState.error.message ?: "Unknown error")
                    }
                }
        }
    }
}
