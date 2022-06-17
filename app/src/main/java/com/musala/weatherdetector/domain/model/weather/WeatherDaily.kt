package com.musala.weatherdetector.domain.model.weather

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.musala.weatherdetector.BR

class WeatherDaily : BaseObservable() {

    @get:Bindable
    var weatherDailyDate: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherDailyDate)
        }

    @get:Bindable
    var weatherDailyIcon: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherDailyIcon)
        }

    @get:Bindable
    var weatherDailyMin: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherDailyMin)
        }

    @get:Bindable
    var weatherDailyMax:  Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherDailyMax)
        }
}
