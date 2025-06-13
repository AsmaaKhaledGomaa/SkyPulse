package com.asoom.skypulse.domain.repo

import android.location.Address
import android.location.Location
import com.asoom.skypulse.domain.model.Coordinates
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getCoordinates(): Coordinates?
    suspend fun getCityName(): String?
}