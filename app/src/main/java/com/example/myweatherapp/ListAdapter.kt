package com.example.myweatherapp

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.weatherdataclasses.hourly_four_day.HourlyData
import com.example.myweatherapp.weatherdataclasses.hourly_four_day.WeatherReport
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var dataList = emptyList<HourlyData>()
    private var timeZone: Int = 0
    private var partOfDay = "d"

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    // convert kelvin to Fahrenheit
    private fun kelvinToFahrenheit(k: Double): Double {
        return (9.0/5.0) * (k - 273.0) + 32.0
    }

    private fun getLocalTime(date: Date, timeZoneOffset: Int, pattern: String): String {
        val formatter = SimpleDateFormat(pattern)
        formatter.timeZone = TimeZone.getTimeZone("UTC")

        val offset: Long = timeZoneOffset.toLong() * 1000

        val offsetDate = Date(date.time + offset)

        return formatter.format(offsetDate)
    }

    private fun isToday(date: Date, timeZoneOffset: Int): Boolean {
        val p: String = "MM/dd/yyyy"
        val givenDay = getLocalTime(date, timeZoneOffset, p)
        val thisDay = getLocalTime(Date(), timeZoneOffset, p)
        return givenDay == thisDay
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var itemView = holder.itemView
        var tv_time = itemView.findViewById<TextView>(R.id.tv_time)
        var tv_temp = itemView.findViewById<TextView>(R.id.tv_temperature)
        var tv_wind = itemView.findViewById<TextView>(R.id.tv_wind)
        var tv_rain = itemView.findViewById<TextView>(R.id.tv_rain)
        var tv_feelsLike = itemView.findViewById<TextView>(R.id.tv_feelsLike)
        var tv_description = itemView.findViewById<TextView>(R.id.tv_description)

        if (partOfDay == "n") {
            val col = Color.WHITE
            tv_time.setTextColor(col)
            tv_temp.setTextColor(col)
            tv_wind.setTextColor(col)
            tv_rain.setTextColor(col)
            tv_feelsLike.setTextColor(col)
            tv_description.setTextColor(col)
        }

        // get current item
        val currentItem = dataList[position]

        val dateTime = Date(currentItem.time.toLong() * 1000)

        tv_time.text = if (isToday(dateTime, timeZone)) {
            "Today, ${getLocalTime(dateTime, timeZone, "HH:mm")}"
        } else {
            getLocalTime(dateTime, timeZone, "MM/dd HH:mm")
        }

        val temp = kelvinToFahrenheit(currentItem.main.temp).toInt()
        val tempStr = "${temp.toString()}° F"
        tv_temp.text = tempStr

        val windSpeed = currentItem.wind.speed
        tv_wind.text = "Wind speed: $windSpeed m/s"

        val pop = currentItem.probOfPrecip * 100
        val percentPrecip = pop.toInt()
        tv_rain.text = "$percentPrecip% chance of precipitation"

        val feelsLike: Int = kelvinToFahrenheit(currentItem.main.feelsLike).toInt()
        tv_feelsLike.text = "Feels like $feelsLike° F"

        val weather = currentItem.weather[0]
        val desc = weather.description
        tv_description.text = "$desc"

    }

    fun setData(weatherData: WeatherReport) {
        this.dataList = weatherData.list
        this.timeZone = weatherData.city.timezone

        partOfDay = weatherData.list[0].sys.partOfDay

        notifyDataSetChanged()
    }

}