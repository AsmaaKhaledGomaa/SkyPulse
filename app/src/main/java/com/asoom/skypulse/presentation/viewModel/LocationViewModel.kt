package com.asoom.skypulse.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asoom.skypulse.domain.usecase.GetCurrentLocationUseCase
import com.asoom.skypulse.presentation.state.LocationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationViewModel(
    private val getCurrentLocation: GetCurrentLocationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocationUiState())
    val uiState: StateFlow<LocationUiState> = _uiState.asStateFlow()

    fun onPermissionResult(isGranted: Boolean) {
        if (isGranted) {
            getLocation()
        } else {
            _uiState.value = _uiState.value.copy(
                isPermissionGranted = false,
                error = "Location permission is required"
            )
        }
    }

    private fun getLocation() {
        _uiState.value = _uiState.value.copy(
            isPermissionGranted = true,
            isLoading = true,
            error = null
        )

        viewModelScope.launch {
            try {
                val coordinates = getCurrentLocation.getCoordinates()
                if (coordinates != null) {
                    val cityName = getCurrentLocation.getCityName()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        latitude = coordinates.latitude,
                        longitude = coordinates.longitude,
                        cityName = cityName,
                        shouldNavigateToWeather = true
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Location not available"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
}
