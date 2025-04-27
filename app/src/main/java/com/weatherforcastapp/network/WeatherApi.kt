package com.weatherforcastapp.network

import com.weatherforcastapp.model.Weather
import com.weatherforcastapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //https://api.openweathermap.org/data/2.5/forecast/daily?q=lisbon&appid=ed60fcfbd110ee65c7150605ea8aceea&units=imperial

    @GET("data/2.5/forecast/daily")
    suspend fun getWeatherData(
        @Query("q") q:String,
        @Query("appid") appid:String=Constants.Api_key,
        @Query("units") units:String="imperial"
    ): Weather
}