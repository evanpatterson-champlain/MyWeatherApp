package com.example.myweatherapp.weatherdataclasses.hourly_four_day


import com.google.gson.annotations.SerializedName

data class WeatherReport(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("message")
    val message: Int,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<HourlyData>,
    @SerializedName("city")
    val city: City
)