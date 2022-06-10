package com.musala.weatherdetector.domain.model.weather

import com.musala.weatherdetector.data.model.weather.WeatherDTO
import retrofit2.Response

interface WeatherGateway {
    suspend fun requestWeatherDetails(
        latitude: String,
        longitude: String
    ): Response<WeatherDTO>
}