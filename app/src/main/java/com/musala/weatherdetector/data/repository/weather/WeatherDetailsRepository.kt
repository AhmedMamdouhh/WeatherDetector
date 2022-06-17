package com.musala.weatherdetector.data.repository.weather

import com.musala.weatherdetector.data.remote.Api
import com.musala.weatherdetector.domain.model.weather.WeatherGateway
import javax.inject.Inject

class WeatherDetailsRepository @Inject constructor(
    private val api: Api
) : WeatherGateway {


    override suspend fun requestWeatherDetails(
        latitude: Double,
        longitude: Double
    ) = api.getWeatherDetails(latitude, longitude)

}