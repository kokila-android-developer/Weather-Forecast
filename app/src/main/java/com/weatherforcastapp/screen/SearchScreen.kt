package com.weatherforcastapp.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.weatherforcastapp.widgets.WeatherAppBar

@Composable
fun SearchScreen(navController: NavController){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            WeatherAppBar(title = "Search",navController=navController, isMainScreen = false,
                icon = Icons.Default.ArrowBack,
                onButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {
         Surface(modifier = Modifier.padding(it).fillMaxSize()) {
         }
    }
}

@Composable
fun CommonTextField(valueState:MutableState<String>,label:String,onValueChange:()->Unit){
    OutlinedTextField(value = valueState.value, maxLines = 1,onValueChange = {
        valueState.value=it
    }, label = { Text(text =label, ) })
}

