package com.musala.weatherdetector.presentation.ui.weather_daily

import androidx.recyclerview.widget.RecyclerView
import com.musala.weatherdetector.databinding.ItemWeatherDailyBinding
import com.musala.weatherdetector.domain.model.weather.WeatherDaily

class WeatherDailyViewHolder(
    private val itemWeatherDailyBinding: ItemWeatherDailyBinding,
) : RecyclerView.ViewHolder(itemWeatherDailyBinding.root) {

    fun bind(weatherDaily: WeatherDaily) {
        itemWeatherDailyBinding.dailyObject = weatherDaily
    }
}