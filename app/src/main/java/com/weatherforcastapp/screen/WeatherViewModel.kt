package com.weatherforcastapp.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherforcastapp.data.DataOrException
import com.weatherforcastapp.model.Weather
import com.weatherforcastapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(val weatherRepository: WeatherRepository):ViewModel() {
    suspend fun getWeather(city:String):DataOrException<Weather,Boolean,Exception>{
        return weatherRepository.getWeather(city)
    }
}