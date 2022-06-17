package com.musala.weatherdetector.domain.util

import android.annotation.SuppressLint
import android.util.Log
import com.musala.weatherdetector.data.model.weather.Daily
import com.musala.weatherdetector.data.model.weather.WeatherDTO
import com.musala.weatherdetector.domain.model.weather.Weather
import com.musala.weatherdetector.domain.model.weather.WeatherDaily
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object WeatherMapper : Mapper<WeatherDTO> {

    override fun mapFrom(weatherDTO: WeatherDTO): Weather {
        val weather = Weather()

        weather.weatherCity = weatherDTO.timezone
        weather.weatherCurrentDate = convertFromTimeStampToDate(weatherDTO.current.dt)
        weather.weatherDescription = weatherDTO.current.weather[0].description
        weather.weatherTemperature = weatherDTO.current.temp.toInt()/10
        weather.weatherHumidity = weatherDTO.current.humidity
        weather.weatherPressure = weatherDTO.current.pressure
        weather.weatherWindSpeed = weatherDTO.current.wind_speed
        weather.weatherFeelsLike = weatherDTO.current.feels_like.toInt()/10
        weather.weatherIcon = weatherDTO.current.weather[0].icon
        weather.weatherDaily = mapFrom(weatherDTO.daily)

        return weather
    }

    private fun mapFrom(dailyList: List<Daily>): List<WeatherDaily> {
        val weatherDailyList = ArrayList<WeatherDaily>()

        for (daily in dailyList) {
            val weatherDaily = WeatherDaily()

            weatherDaily.weatherDailyDate = convertFromTimeStampToDate(daily.dt)
            weatherDaily.weatherDailyIcon = daily.weather[0].icon
            weatherDaily.weatherDailyMax = daily.temp.max.toInt()/10
            weatherDaily.weatherDailyMin = daily.temp.min.toInt()/10

            weatherDailyList.add(weatherDaily)

        }

        return weatherDailyList
    }


    @SuppressLint("SimpleDateFormat")
    fun convertFromTimeStampToDate(time: Long): String {
        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(time * 1000)
    }


}
