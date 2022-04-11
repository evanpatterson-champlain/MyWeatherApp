package com.example.myweatherapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView.OnEditorActionListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myweatherapp.weatherdataclasses.hourly_four_day.WeatherReport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchFragment : Fragment() {

    private val apiKey: String = BuildConfig.API_KEY

    private lateinit var btn_search: Button
    private lateinit var et_input: EditText

    private fun initViews(view: View) {
        btn_search = view.findViewById<Button>(R.id.btn_search)
        et_input = view.findViewById<EditText>(R.id.et_enterCity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initViews(view)



        btn_search.setOnClickListener {
            performSearch()
        }

        // makes the virtual enter button work as a search button
        et_input.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })

        return view
    }

    private fun performSearch() {
        val userInput = et_input.text.toString()
        if (userInput.isNotEmpty()) {
            searchCity(userInput)
        }
    }

    private fun searchCity(cityName: String) {
        val url = "https://api.openweathermap.org/data/2.5/"

        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var weatherAPI: WeatherAPI = retrofit.create(WeatherAPI::class.java)


        var call: Call<WeatherReport> = weatherAPI.getWeather(cityName, apiKey)


        call.enqueue(object : Callback<WeatherReport> {

            override fun onResponse(call: Call<WeatherReport>, response: Response<WeatherReport>) {

                if (!response.isSuccessful) {
                    Log.d("Response", "not successful")
                    return
                }

                val responseBody: WeatherReport? = response.body()

                if (responseBody != null) {
                    // get the non-null asserted report
                    val weatherReport: WeatherReport = responseBody!!

                    // get the mainActivity
                    val mainActivity: MainActivity = requireActivity() as MainActivity

                    // set the data and show the report
                    mainActivity.reportViewModel.liveWeatherData.value = weatherReport
                    et_input.text.clear()
                    goToReport()
                }
            }

            override fun onFailure(call: Call<WeatherReport>, t: Throwable) {
                Log.d("Response", t.message.toString())
            }

        })



    }

    // go to the report fragment
    private fun goToReport() {
        findNavController().navigate(R.id.action_search_to_report)
    }

}


