package com.weatherforcastapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.weatherforcastapp.R
import com.weatherforcastapp.model.WeatherItem
import com.weatherforcastapp.utils.Constants
import com.weatherforcastapp.utils.formatDateToTime
import com.weatherforcastapp.utils.formateDateToDay

@Composable
fun SetRowItemDetail(item: WeatherItem) {
    Surface(modifier = Modifier.padding(3.dp).height(60.dp).fillMaxWidth(), color = Color.White, shadowElevation = 3.dp,
        shape = RoundedCornerShape(topStart =10.dp, bottomStart = 10.dp, bottomEnd = 10.dp)
    ) {
        Row (modifier = Modifier.padding(5.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = formateDateToDay(item.dt.toLong()), style = MaterialTheme.typography.titleMedium, fontSize = 14.sp, color = Color.Black)
            WeatherImageState(Constants.colour_img_url+"${item.weather[0].icon}.png")
            Box(contentAlignment = Alignment.Center, modifier = Modifier.background( color = Color(0xFFFFC107), shape = RoundedCornerShape(10.dp))) {
                Text(text= item.weather[0].description, style = MaterialTheme.typography.bodyMedium, fontSize = 13.sp, color = Color.Black,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp))
            }
            Row (horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 10.dp)){
                Text(text = "${item.temp.min}°", fontSize = 16.sp, style = MaterialTheme.typography.titleLarge,
                    color = Color.Black)
                Text(text = "${item.temp.max}°", fontSize = 16.sp, style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray, modifier = Modifier.padding(start =10.dp))
            }

        }
    }
}
@Composable
fun SunSetAndRise(weatherItem: WeatherItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(12.dp)
    ) {
        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id= R.drawable.sunrise), contentDescription = "sun_rise",
                tint = Color.Black, modifier = Modifier.size(20.dp))
            Text(text= formatDateToTime(weatherItem.sunrise.toLong()), fontSize = 12.sp, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
        }
        Row(modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(text= formatDateToTime(weatherItem.sunset.toLong()), style = MaterialTheme.typography.bodyMedium, fontSize = 12.sp, color = Color.Black)
            Icon(painter = painterResource(id= R.drawable.sunset), contentDescription = "sun_set",
                tint = Color.Black, modifier = Modifier.size(20.dp))
        }

    }
}
@Composable
fun WeatherImageState(image_url: String) {
    AsyncImage(model = image_url, contentDescription = "", modifier = Modifier.size(80.dp))
}
@Composable
fun HumidityWindPressure(weather: WeatherItem){
    Row(modifier = Modifier.padding(12.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(painter = painterResource(id= R.drawable.humidity), contentDescription = "humidity"
                , modifier = Modifier.size(20.dp).padding(end = 3.dp), tint = Color.Black)
            Text(text = "${weather.humidity}%", color = Color.Black, fontSize = 12.sp, style = MaterialTheme.typography.bodyMedium)
        }
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(painter = painterResource(id= R.drawable.pressure), contentDescription = "pressure"
                , modifier = Modifier.size(20.dp).padding(end = 3.dp), tint = Color.Black)
            Text(text = "${weather.pressure} psi", color = Color.Black, fontSize = 12.sp, style = MaterialTheme.typography.bodyMedium)
        }
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(painter = painterResource(id= R.drawable.wind), contentDescription = "wind"
                , modifier = Modifier.size(20.dp).padding(end = 3.dp), tint = Color.Black)
            Text(text = "${weather.humidity} mph", color = Color.Black, fontSize = 12.sp, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

