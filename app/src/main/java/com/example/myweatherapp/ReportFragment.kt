package com.example.myweatherapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.weatherdataclasses.hourly_four_day.WeatherReport
import java.util.*


class ReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_report, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_report)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val mainActivity: MainActivity = requireActivity() as MainActivity
        mainActivity.reportViewModel.liveWeatherData.observe(viewLifecycleOwner, Observer { data ->
            val partOfDay = data.list[0].sys.partOfDay
            if (partOfDay == "n") {
                view.findViewById<TextView>(R.id.reportHeader).setTextColor(Color.WHITE)
                view.setBackgroundResource(R.drawable.bg_night)
            }
            view.findViewById<TextView>(R.id.reportHeader).text = data.city.name
            adapter.setData(data)
        })

        return view
    }

    private fun goToSearch() {
        findNavController().navigate(R.id.action_report_to_search)
    }

}

