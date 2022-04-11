package com.example.myweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {

    lateinit var reportViewModel: ReportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reportViewModel = ViewModelProvider(this).get(ReportViewModel::class.java)

        val nc: NavController = findNavController(R.id.fragment_main)
        setupActionBarWithNavController(nc)
    }

    override fun onSupportNavigateUp(): Boolean {
        val nc: NavController = findNavController(R.id.fragment_main)
        return nc.navigateUp() || super.onSupportNavigateUp()
    }


}