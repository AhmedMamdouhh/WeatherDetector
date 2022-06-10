package com.musala.weatherdetector.domain.model.city

data class CityItem(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String
)