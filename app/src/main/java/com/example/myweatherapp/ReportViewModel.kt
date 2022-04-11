package com.example.myweatherapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.weatherdataclasses.hourly_four_day.HourlyData
import com.example.myweatherapp.weatherdataclasses.hourly_four_day.WeatherReport


class ReportViewModel: ViewModel() {
    var liveWeatherData: MutableLiveData<WeatherReport> = MutableLiveData<WeatherReport>()
}