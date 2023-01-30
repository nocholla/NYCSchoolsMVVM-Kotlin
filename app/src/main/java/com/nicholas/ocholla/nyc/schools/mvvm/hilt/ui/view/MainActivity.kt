package com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.R
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.adapter.SchoolListAdapter
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.viewmodel.SchoolViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val schoolViewModel : SchoolViewModel by viewModels()
    private lateinit var adapter: SchoolListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SchoolListAdapter(this, arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

}