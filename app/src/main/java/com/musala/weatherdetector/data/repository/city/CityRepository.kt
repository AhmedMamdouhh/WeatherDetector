package com.musala.weatherdetector.data.repository.city

import com.musala.weatherdetector.data.remote.Api
import com.musala.weatherdetector.domain.model.city.CityGateway
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val api: Api
) : CityGateway {

    override suspend fun requestCityLocation(cityName: String) =
        api.getCityLocationByName(cityName)
}