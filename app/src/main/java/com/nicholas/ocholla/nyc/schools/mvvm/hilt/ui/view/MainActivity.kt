package com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.R
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.adapter.SchoolListAdapter
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.viewmodel.SchoolViewModel

class MainActivity : AppCompatActivity() {

    private val schoolViewModel : SchoolViewModel by viewModels()
    private lateinit var adapter: SchoolListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}