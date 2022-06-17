package com.musala.weatherdetector.presentation.ui.weather_details

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import com.musala.weatherdetector.R
import com.musala.weatherdetector.databinding.FragmentWeatherDetailsBinding
import com.musala.weatherdetector.presentation.ui.weather_daily.WeatherDailyAdapter
import com.musala.weatherdetector.presentation.utils.manager.ResponseManager
import javax.inject.Inject
import com.musala.weatherdetector.presentation.utils.*


@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private lateinit var weatherDetailsBinding: FragmentWeatherDetailsBinding
    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels()
    private lateinit var weatherDailyAdapter: WeatherDailyAdapter

    @Inject
    lateinit var responseManager: ResponseManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        weatherDetailsBinding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)

        permissionCheck()
        observeWeatherDetailsSuccess()

        return weatherDetailsBinding.root
    }

    private fun observeWeatherDetailsSuccess() {
        weatherDetailsViewModel.observeWeatherDetailsSuccess.observe(
            viewLifecycleOwner,
            EventObserver { weather ->
                weatherDetailsBinding.weatherObject = weather
                weatherDailyAdapter = WeatherDailyAdapter(weather.weatherDaily)
                weatherDetailsBinding.apply {
                    rvWeatherDetailsDailyList.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
                        adapter = weatherDailyAdapter
                        recyclerAnimationExtension(this)
                    }
                }

            })
    }

    fun permissionCheck() {
        if (checkForLocationPermission() == Constants.PERMISSION_GRANTED)
            if (isLocationEnabled()) {
                showAndHideWeatherDetails(Constants.SHOW, Constants.HIDE)
                getCurrentLocation()
            } else {
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

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {

        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                try {
                    weatherDetailsViewModel.getWeatherDetails(
                        location!!.longitude,
                        location.longitude
                    )
                } catch (ex: NullPointerException) {
                    showAndHideWeatherDetails(Constants.HIDE, Constants.SHOW)
                    responseManager.failed(getString(R.string.error_weather_details_gps_problem))
                }
            }
    }
}