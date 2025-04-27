package com.weatherforcastapp.di

import com.weatherforcastapp.network.WeatherApi
import com.weatherforcastapp.repository.WeatherRepository
import com.weatherforcastapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideWeatherApi():WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Constants.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(api: WeatherApi)=WeatherRepository(api)
}