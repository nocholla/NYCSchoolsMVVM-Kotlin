package com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.R
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model.School
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.adapter.SchoolListAdapter
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.viewmodel.SchoolViewModel
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.util.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val schoolViewModel : SchoolViewModel by viewModels()
    private lateinit var adapter: SchoolListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupObserver()
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

    private fun setupObserver() {
        schoolViewModel.schools.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    tvError.visibility = View.GONE
                    it.data?.let { schools -> renderList(schools) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    tvError.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    // Handle Error
                    progressBar.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(schools: List<School>) {
        adapter.updateSchools(schools)
        adapter.notifyDataSetChanged()
    }

    companion object {
        val TAG = "MainActivity"

        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

}