package com.asoom.skypulse.domain.usecase

import android.location.Location
import com.asoom.skypulse.domain.model.Coordinates
import com.asoom.skypulse.domain.repo.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentLocationUseCase(
    private val repository: LocationRepository
) {
    suspend fun getCoordinates(): Coordinates? = repository.getCoordinates()
    suspend fun getCityName(): String? = repository.getCityName()
}