package com.asoom.skypulse.domain.repository

interface LocationRepository {
    suspend fun getCityName(latitude: Double, longitude: Double): String?
} 