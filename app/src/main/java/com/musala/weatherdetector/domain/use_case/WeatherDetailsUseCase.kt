package com.musala.weatherdetector.domain.use_case

import com.musala.weatherdetector.data.repository.weather.WeatherDetailsRepository
import com.musala.weatherdetector.data.utils.Resource
import com.musala.weatherdetector.domain.model.weather.Weather
import com.musala.weatherdetector.domain.util.BaseDataSource
import com.musala.weatherdetector.domain.util.Mapper
import com.musala.weatherdetector.domain.util.WeatherMapper
import javax.inject.Inject

class WeatherDetailsUseCase @Inject constructor(
    private val weatherDetailsRepository: WeatherDetailsRepository,
    private val weatherMapper: WeatherMapper
) : BaseDataSource() {

    suspend fun requestWeatherDetails(
        latitude: Double,
        longitude: Double):Resource<Weather> {
        val weatherDTO = safeApiCall(weatherMapper as Mapper<Any>) {
            weatherDetailsRepository.requestWeatherDetails(
                latitude,
                longitude
            )
        }

        return weatherDTO as Resource<Weather>
    }
}
