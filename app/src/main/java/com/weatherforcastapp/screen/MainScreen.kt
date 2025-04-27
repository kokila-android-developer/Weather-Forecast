package com.weatherforcastapp.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.weatherforcastapp.data.DataOrException
import com.weatherforcastapp.model.Weather
import com.weatherforcastapp.navigation.WeatherNavigation
import com.weatherforcastapp.navigation.WeatherScreen
import com.weatherforcastapp.utils.Constants
import com.weatherforcastapp.utils.formatDateToString
import com.weatherforcastapp.utils.formatDecimal
import com.weatherforcastapp.widgets.HumidityWindPressure
import com.weatherforcastapp.widgets.SetRowItemDetail
import com.weatherforcastapp.widgets.SunSetAndRise
import com.weatherforcastapp.widgets.WeatherAppBar
import com.weatherforcastapp.widgets.WeatherImageState

@Composable
fun MainScreen(navController: NavController, viewModel: WeatherViewModel){
    val weatherData= produceState<DataOrException<Weather,Boolean,Exception>>(
        initialValue = DataOrException(loading = true)
    ){
        value=viewModel.getWeather("Chennai")
    }.value
    Log.d("TAG", "ShowData: ${weatherData.loading} ${weatherData.data}")
    if(weatherData.loading!!){
        CircularProgressIndicator()
    }else if(weatherData.data!=null){
        MainScaffold(weatherData.data,navController)
    }else if(weatherData.e!=null){
        Text(text = weatherData.e.message.toString())
    }
}
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(
        Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
           WeatherAppBar(
               title = "${weather.city.name},${weather.city.country}",
               isMainScreen = true,
               elevation = 3.dp,
               navController = navController,
               onAddClicked = {
                   navController.navigate(WeatherScreen.SearchScreen.name)
               },
               onButtonClicked = {
                   Log.d("TAG", "MainScaffold:back clicked ")
               }
           )
        }
    ) {paddingValues ->
        Surface (modifier = Modifier.padding(paddingValues).fillMaxSize().background(color = Color.White)){
            MainContent(weather)
        }

    }
}
@Composable
fun MainContent(weather: Weather) {
    Column(modifier = Modifier.background(color = Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text= formatDateToString(weather.list[0].dt.toLong()),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            fontSize = 13.sp)
        Surface (
            modifier = Modifier.size(200.dp).padding(2.dp),
            shape = CircleShape,
            color = Color(0xFFFFC107),
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                WeatherImageState(Constants.colour_img_url+"${weather.list[0].weather[0].icon}.png")
                Text(text = "${formatDecimal(weather.list[0].temp.day)}°", style =MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.ExtraBold )
                Text(text = weather.list[0].weather[0].main, style =MaterialTheme.typography.titleMedium,
                    fontSize = 15.sp, color = Color.Black, fontStyle = FontStyle.Italic
                )
            }
        }
        HumidityWindPressure(weather.list[0])
        HorizontalDivider(modifier = Modifier.fillMaxWidth().height(1.dp).padding(2.dp), color = Color.LightGray)
        SunSetAndRise(weather.list[0])
        Text(text="This Week", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        Surface(modifier = Modifier.fillMaxSize().padding(5.dp),
            color = Color.LightGray, shape = RoundedCornerShape(10.dp)) {
            LazyColumn {
                items(weather.list) {item->
                    SetRowItemDetail(item)
                }
            }
        }
    }
}

