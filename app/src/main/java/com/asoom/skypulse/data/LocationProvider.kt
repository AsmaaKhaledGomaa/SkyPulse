package com.asoom.skypulse.data

import android.location.Location
import kotlinx.coroutines.flow.Flow


interface LocationProvider {
    fun getCurrentLocation(): Flow<Location>
}