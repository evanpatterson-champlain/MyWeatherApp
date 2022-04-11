package com.example.myweatherapp.weatherdataclasses.hourly_four_day


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    val partOfDay: String
)