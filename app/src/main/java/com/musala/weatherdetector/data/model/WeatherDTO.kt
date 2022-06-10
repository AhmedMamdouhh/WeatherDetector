package com.musala.weatherdetector.data.model

data class WeatherDTO(
    val current: Current,
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)