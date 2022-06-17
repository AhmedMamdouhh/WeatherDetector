package com.musala.weatherdetector.presentation.ui.weather_daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musala.weatherdetector.databinding.ItemWeatherDailyBinding
import com.musala.weatherdetector.domain.model.weather.WeatherDaily

class WeatherDailyAdapter(
    private val weatherDailyList: List<WeatherDaily>
) : RecyclerView.Adapter<WeatherDailyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDailyViewHolder =
        WeatherDailyViewHolder(
            ItemWeatherDailyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WeatherDailyViewHolder, position: Int) {
        holder.bind(weatherDailyList[position])
    }

    override fun getItemCount(): Int {
        return weatherDailyList.size
    }

}