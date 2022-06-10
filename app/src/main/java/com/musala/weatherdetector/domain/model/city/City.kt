package com.musala.weatherdetector.domain.model.city

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.musala.weatherdetector.BR


class City : BaseObservable() {

    @get:Bindable
    var cityLatitude: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.cityLatitude)
        }

    @get:Bindable
    var cityLongitude: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.cityLongitude)
        }

    @get:Bindable
    var cityName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.cityName)
        }
}