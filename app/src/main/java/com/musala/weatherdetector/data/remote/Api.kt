package com.musala.weatherdetector.data.remote

import com.musala.weatherdetector.BuildConfig
import com.musala.weatherdetector.data.model.city.CityDTO
import com.musala.weatherdetector.data.model.weather.WeatherDTO
import com.musala.weatherdetector.data.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface Api {

    //Get lat&lng
    @GET("geo/1.0/direct")
    suspend fun getCityLocationByName(
        @Query(Constants.CITY_NAME)
        searchKeyWord: String,
        @Query(Constants.API_KEY)
        apiKey: String = BuildConfig.API_KEY
    ): Response<CityDTO>

    //Get
    @GET("data/2.5/weather")
    suspend fun getWeatherDetails(
        @Query(Constants.LATITUDE)
        latitude:Double,
        @Query(Constants.LONGITUDE)
        longitude:Double,
        @Query(Constants.EXCLUDE)
        exclude:String = Constants.EXCEPT,
        @Query(Constants.API_KEY)
        apiKey: String = BuildConfig.API_KEY,
    ): Response<WeatherDTO>

}