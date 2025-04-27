package com.weatherforcastapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.weatherforcastapp.screen.MainScreen
import com.weatherforcastapp.screen.WeatherSplashScreen
import com.weatherforcastapp.screen.WeatherViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.weatherforcastapp.screen.SearchScreen


@Composable
fun WeatherNavigation(){
    val navController= rememberNavController()
    NavHost(navController=navController, startDestination = WeatherScreen.SplashScreen.name) {
        composable(WeatherScreen.SplashScreen.name){
            WeatherSplashScreen(navController)
        }
        composable(WeatherScreen.MainScreen.name){
            val viewModel= hiltViewModel<WeatherViewModel>()
            MainScreen(navController,viewModel)
        }
        composable(WeatherScreen.SearchScreen.name){
            SearchScreen(navController)
        }
    }
}