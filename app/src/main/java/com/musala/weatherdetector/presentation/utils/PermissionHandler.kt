package com.musala.weatherdetector.presentation.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.musala.weatherdetector.presentation.ui.weather_details.WeatherDetailsFragment


val Fragment.requestLocationPermission
    get() = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { result ->
        if (result) {
            (this as WeatherDetailsFragment).permissionCheck()
        } else {
            (this as WeatherDetailsFragment).showAndHideWeatherDetails(Constants.HIDE,Constants.SHOW)
        }
    }


fun Fragment.checkForLocationPermission(): Int {
    return when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) -> {
            Constants.PERMISSION_GRANTED
        }
        else -> {
            requestLocationPermission.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            0
        }
    }
}


fun Fragment.isLocationEnabled(): Boolean {
    val locationManager: LocationManager =
        requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
        LocationManager.NETWORK_PROVIDER
    )
}