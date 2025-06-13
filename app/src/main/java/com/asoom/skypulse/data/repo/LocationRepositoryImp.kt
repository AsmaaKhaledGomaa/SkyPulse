package com.asoom.skypulse.data.repo

import android.content.Context
import android.location.Geocoder
import com.asoom.skypulse.data.LocationProvider
import com.asoom.skypulse.domain.model.Coordinates
import com.asoom.skypulse.domain.repo.LocationRepository
import kotlinx.coroutines.flow.firstOrNull

class LocationRepositoryImpl(
    private val context: Context,
    private val provider: LocationProvider
) : LocationRepository {

    override suspend fun getCoordinates(): Coordinates? {
        return provider.getCurrentLocation()
            .firstOrNull()
            ?.let { location ->
                Coordinates(location.latitude, location.longitude)
            }
    }

    override suspend fun getCityName(): String? {
        val coordinates = getCoordinates() ?: return null
        val geocoder = Geocoder(context, java.util.Locale.getDefault())

        return kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            try {
                val addresses = geocoder.getFromLocation(coordinates.latitude, coordinates.longitude, 1)
                addresses?.firstOrNull()?.locality
            } catch (e: Exception) {
                null
            }
        }
    }
}
