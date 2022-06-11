package com.musala.weatherdetector.domain.util

import android.annotation.SuppressLint
import com.musala.weatherdetector.data.model.weather.Daily
import com.musala.weatherdetector.data.model.weather.WeatherDTO
import com.musala.weatherdetector.domain.model.weather.Weather
import com.musala.weatherdetector.domain.model.weather.WeatherDaily
import java.text.SimpleDateFormat
import java.util.*

object WeatherMapper:Mapper<WeatherDTO> {

    override fun mapFrom(weatherDTO: WeatherDTO): Weather {
        val weather = Weather()

        weather.weatherCity = weatherDTO.timezone
        weather.weatherCurrentDate = convertFromTimeStampToDate(weatherDTO.current.dt)
        weather.weatherDescription = weatherDTO.current.weather[0].description
        weather.weatherTemperature = weatherDTO.current.temp
        weather.weatherHumidity = weatherDTO.current.humidity
        weather.weatherPressure = weatherDTO.current.pressure
        weather.weatherWindSpeed = weatherDTO.current.wind_speed
        weather.weatherIcon = weatherDTO.current.weather[0].icon
        weather.weatherDaily = mapFrom(weatherDTO.daily)

        return weather
    }
    private fun mapFrom(dailyList: List<Daily>): List<WeatherDaily> {
        val weatherDaily = WeatherDaily()
        val weatherDailyList = mutableListOf<WeatherDaily>()

        for (daily in dailyList) {
            weatherDaily.weatherDailyDate = convertFromTimeStampToDate(daily.dt)
            weatherDaily.weatherDailyIcon = daily.weather[0].icon
            weatherDaily.weatherDailyMax = daily.temp.max
            weatherDaily.weatherDailyMin = daily.temp.min

            weatherDailyList.add(weatherDaily)
        }

        return weatherDailyList
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertFromTimeStampToDate(timeStampDate: Int): String {
        return try {
            SimpleDateFormat("MM/dd/yyyy HH:mm").format(Date(timeStampDate.toLong()))
        } catch (e: Exception) {
            ""
        }
    }

}
