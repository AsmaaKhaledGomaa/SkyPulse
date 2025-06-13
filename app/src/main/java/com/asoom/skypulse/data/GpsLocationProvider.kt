package com.asoom.skypulse.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class GpsLocationProvider(
    private val context: Context
) : LocationProvider {

    override fun getCurrentLocation(): Flow<Location> = callbackFlow {
        if (
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            close(SecurityException("Location permission not granted"))
            return@callbackFlow
        }

        val fusedClient = LocationServices.getFusedLocationProviderClient(context)
        val cancellationToken = CancellationTokenSource()

        fusedClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellationToken.token
        ).addOnSuccessListener { location ->
            if (location != null) trySend(location)
            else close(NullPointerException("Location is null"))
            close()
        }.addOnFailureListener { exception ->
            close(exception)
        }

        awaitClose { cancellationToken.cancel() }
    }
}
