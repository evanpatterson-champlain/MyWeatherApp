package com.example.myweatherapp.weatherdataclasses.hourly_four_day


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val cloudPcnt: Int
)