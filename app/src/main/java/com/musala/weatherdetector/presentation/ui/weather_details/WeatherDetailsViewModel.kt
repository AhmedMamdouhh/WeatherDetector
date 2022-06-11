package com.musala.weatherdetector.presentation.ui.weather_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musala.weatherdetector.data.utils.Resource
import com.musala.weatherdetector.domain.use_case.CityUseCase
import com.musala.weatherdetector.presentation.utils.manager.ResponseManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val cityUseCase: CityUseCase,
    private val responseManager: ResponseManager
):ViewModel() {

    init {
        getCityLocation("Egypt")
    }
     fun getCityLocation(cityName:String){
        viewModelScope.launch {
            when(val response = cityUseCase.requestCityLocation(cityName)){
                is Resource.Success -> {
                    Log.e("test", "getCityLocation: ${response.data!![0].country}" )
                }
            }
        }
    }
}