package com.weatherforcastapp.repository

import android.util.Log
import com.weatherforcastapp.data.DataOrException
import com.weatherforcastapp.model.Weather
import com.weatherforcastapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(val api: WeatherApi) {
    suspend fun getWeather(city:String):DataOrException<Weather,Boolean,Exception>{
        val response=try{
            api.getWeatherData(q=city)
        }catch (e:Exception){
            Log.d("TAG", "getWeather: ${e.message}")
            return DataOrException(e=e, loading = false)
        }
        Log.d("TAG", "getWeather: $response")
        return DataOrException(data = response, loading = false)
    }
}