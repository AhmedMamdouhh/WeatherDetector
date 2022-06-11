package com.musala.weatherdetector.domain.use_case

import com.musala.weatherdetector.domain.model.city.CityGateway
import com.musala.weatherdetector.domain.util.BaseDataSource
import com.musala.weatherdetector.domain.util.WeatherMapper
import javax.inject.Inject

class CityUseCase @Inject constructor(
    private val cityRepository: CityGateway) : BaseDataSource() {

    suspend fun requestCityLocation(cityName: String) =
        safeApiCall(null) { cityRepository.requestCityLocation(cityName) }

}