package com.musala.weatherdetector.domain.model.weather

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.musala.weatherdetector.BR
import com.musala.weatherdetector.data.model.weather.Daily

class Weather : BaseObservable() {

    @get:Bindable
    var weatherCity: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherCity)
        }

    @get:Bindable
    var weatherCurrentDate: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherCurrentDate)
        }

    @get:Bindable
    var weatherDescription: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherDescription)
        }

    @get:Bindable
    var weatherTemperature: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherTemperature)
        }

    @get:Bindable
    var weatherHumidity: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherHumidity)
        }

    @get:Bindable
    var weatherPressure: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherPressure)
        }

    @get:Bindable
    var weatherWindSpeed: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherWindSpeed)
        }

    @get:Bindable
    var weatherFeelsLike: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherFeelsLike)
        }

    @get:Bindable
    var weatherIcon: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherIcon)
        }

    @get:Bindable
    var weatherDaily: List<WeatherDaily> = listOf()
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherDaily)
        }
}