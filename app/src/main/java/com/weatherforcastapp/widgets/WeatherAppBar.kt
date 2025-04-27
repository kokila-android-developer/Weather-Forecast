package com.weatherforcastapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title:String="",
    icon:ImageVector?=null,
    isMainScreen:Boolean=true,
    elevation:Dp =0.dp,
    navController: NavController,
    onAddClicked: () -> Unit={},
    onButtonClicked: () -> Unit={},
    modifier: Modifier=Modifier
){
    TopAppBar(
        title = { Text(text = title,modifier=Modifier.padding(5.dp),
            textAlign = TextAlign.Center, style = MaterialTheme.typography.titleMedium) },
        modifier = modifier
            .padding(5.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(3.dp)),
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.White, titleContentColor = Color.Black),
        actions = {
         if(isMainScreen){
             IconButton(onClick = {
                 onAddClicked.invoke()
             }) {
                 Icon(imageVector = Icons.Default.Search, contentDescription = "search", tint = Color.Black)
             }
             IconButton(onClick = {}) {
                 Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more", tint = Color.Black)
             }
         }else{
             Box(){}
         }
        },
        navigationIcon = {
            if(icon!=null){
                Icon(modifier = Modifier.clickable { onButtonClicked.invoke() },imageVector = icon, contentDescription = "back", tint = Color.Black)
            }
        }
    )
}