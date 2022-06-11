package com.musala.weatherdetector.presentation.ui.weather_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.musala.weatherdetector.databinding.FragmentWeatherDetailsBinding
import com.musala.weatherdetector.domain.model.weather.Weather
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private lateinit var weatherDetailsBinding: FragmentWeatherDetailsBinding
    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        weatherDetailsBinding = FragmentWeatherDetailsBinding.inflate(inflater,container,false)

        weatherDetailsBinding.weatherObject = Weather()
        weatherDetailsViewModel.getCityLocation("Egypt")

        return weatherDetailsBinding.root
    }
}