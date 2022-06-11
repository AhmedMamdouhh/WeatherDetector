package com.musala.weatherdetector.di

import com.musala.weatherdetector.data.remote.Api
import com.musala.weatherdetector.data.repository.city.CityRepository
import com.musala.weatherdetector.domain.model.city.CityGateway
import com.musala.weatherdetector.domain.use_case.CityUseCase
import com.musala.weatherdetector.domain.util.WeatherMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun providesCityRepository(api: Api): CityGateway =
        CityRepository(api)

    @Provides
    fun provideWeatherMapper() = WeatherMapper

}