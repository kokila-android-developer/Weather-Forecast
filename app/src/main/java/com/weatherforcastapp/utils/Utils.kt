package com.weatherforcastapp.utils

import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDateToString(timeStamp:Long):String{
    Log.d("TAG", "formatDateToString:$timeStamp ")
    val date = Date(timeStamp*1000)
    val dateFormat=SimpleDateFormat("EEE, MMM dd", Locale.getDefault())
    return dateFormat.format(date)
}

fun formateDateToDay(timeStamp: Long):String{
    val date=Date(timeStamp*1000)
    val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())
    return dayFormat.format(date)
}

fun formatDateToTime(timeStamp:Long):String{
    val date=Date(timeStamp*1000)
    val timeFormat=SimpleDateFormat("hh:mm:ss a",Locale.getDefault())
    return  timeFormat.format(date)
}

fun formatDecimal(item:Double):String{
    return "%.0f".format(item)
}