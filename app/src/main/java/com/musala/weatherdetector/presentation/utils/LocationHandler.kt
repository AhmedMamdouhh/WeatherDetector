package com.musala.weatherdetector.presentation.utils

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.musala.weatherdetector.domain.model.city.City
import java.util.*


@SuppressLint("MissingPermission")
fun Fragment.getCurrentLocation(): City {
    val mFusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(requireActivity())

    val city = City()

    mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
        val location: Location? = task.result
        if (location != null) {

            val geoCoder = Geocoder(requireActivity().applicationContext, Locale.getDefault())
            val address = geoCoder.getFromLocation(location.latitude, location.longitude, 1)

            city.cityLongitude = location.longitude
            city.cityLatitude = location.latitude
            city.cityName = address[0].countryName

        }
    }

    return city
}


