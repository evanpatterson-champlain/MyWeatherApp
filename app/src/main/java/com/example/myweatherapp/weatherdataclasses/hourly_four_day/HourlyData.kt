package com.example.myweatherapp.weatherdataclasses.hourly_four_day


import com.google.gson.annotations.SerializedName

data class HourlyData(
    @SerializedName("dt")
    val time: Int,
    @SerializedName("main")
    val main: Main,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("pop")
    val probOfPrecip: Double,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("rain")
    val rain: Rain
)