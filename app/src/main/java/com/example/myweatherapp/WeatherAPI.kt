package com.example.myweatherapp

import com.example.myweatherapp.weatherdataclasses.hourly_four_day.WeatherReport
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherAPI {

    @GET("forecast")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") appid: String
    ): Call<WeatherReport>

}