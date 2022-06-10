package com.musala.weatherdetector.domain.model.city

import com.musala.weatherdetector.data.model.city.CityDTO
import retrofit2.Response

interface CityGateway {
    suspend fun requestCityLocation(
        cityName: String
    ): Response<CityDTO>
}