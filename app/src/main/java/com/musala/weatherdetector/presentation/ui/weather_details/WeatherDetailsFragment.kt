package com.musala.weatherdetector.presentation.ui.weather_details

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.musala.weatherdetector.databinding.FragmentWeatherDetailsBinding
import com.musala.weatherdetector.domain.model.weather.Weather
import dagger.hilt.android.AndroidEntryPoint
import com.musala.weatherdetector.R
import com.musala.weatherdetector.presentation.utils.manager.ResponseManager
import javax.inject.Inject
import android.content.DialogInterface
import android.app.AlertDialog
import android.view.View.GONE
import com.musala.weatherdetector.presentation.utils.*
import kotlinx.coroutines.currentCoroutineContext


@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private lateinit var weatherDetailsBinding: FragmentWeatherDetailsBinding
    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels()

    @Inject
    lateinit var responseManager: ResponseManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        weatherDetailsBinding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)

        weatherDetailsBinding.weatherObject = Weather()
        weatherDetailsViewModel.getCityLocation("Egypt")

        permissionCheck()
        return weatherDetailsBinding.root
    }

    fun permissionCheck() {
        if (checkForLocationPermission() == Constants.PERMISSION_GRANTED)
            if (isLocationEnabled()) {
                showAndHideWeatherDetails(Constants.SHOW,Constants.HIDE)
                getCurrentLocation()
            }
            else {
                responseManager.failed(getString(R.string.error_weather_details_enable_gps))
                showAndHideWeatherDetails(Constants.HIDE, Constants.SHOW)
            }
    }

    fun showAndHideWeatherDetails(showHideDetails: Int, showHideText: Int) {
        weatherDetailsBinding.apply {
            cvWeatherDetailsCurrentContainer.visibility = showHideDetails
            cvWeatherDetailsAirQualityContainer.visibility = showHideDetails
            cvWeatherDetailsTempContainer.visibility = showHideDetails

            tvWeatherDetailsNoCurrentLocation.visibility = showHideText
        }
    }

}