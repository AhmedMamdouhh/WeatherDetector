package com.musala.weatherdetector.presentation.ui.weather_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musala.weatherdetector.data.utils.Resource
import com.musala.weatherdetector.domain.model.weather.Weather
import com.musala.weatherdetector.domain.use_case.CityUseCase
import com.musala.weatherdetector.domain.use_case.WeatherDetailsUseCase
import com.musala.weatherdetector.presentation.utils.Event
import com.musala.weatherdetector.presentation.utils.manager.ResponseManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val cityUseCase: CityUseCase,
    private val weatherDetailsUseCase: WeatherDetailsUseCase,
    private val responseManager: ResponseManager
) : ViewModel() {

    private val _observeWeatherDetailsSuccess = MutableLiveData<Event<Weather>>()


    fun getCityLocation(cityName: String) {
        viewModelScope.launch {
            when (val response = cityUseCase.requestCityLocation(cityName)) {
                is Resource.Success -> {
                    getWeatherDetails(response.data!![0].lat, response.data[0].lon)
                }
            }
        }
    }

    fun getWeatherDetails(
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            when (val response = weatherDetailsUseCase.requestWeatherDetails(latitude,longitude)){
                is Resource.Success ->{
                    _observeWeatherDetailsSuccess.value = Event(response.data!!)
                }
                is Resource.Failed ->{
                }
                else ->{
                }
            }
        }
    }


    //Getters:
    val observeWeatherDetailsSuccess: LiveData<Event<Weather>>
        get() = _observeWeatherDetailsSuccess
}