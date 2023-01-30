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
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model.Score
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.adapter.ScoreListAdapter
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.viewmodel.ScoreViewModel
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.util.Status
import kotlinx.android.synthetic.main.activity_main.*

class ScoresActivity : AppCompatActivity() {

    private val scoreViewModel : ScoreViewModel by viewModels()
    private lateinit var adapter: ScoreListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ScoreListAdapter(this, arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        scoreViewModel.scores.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    tvError.visibility = View.GONE
                    it.data?.let { scores -> renderList(scores) }
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

    private fun renderList(scores: List<Score>) {
        adapter.updateScores(scores)
        adapter.notifyDataSetChanged()
    }

    companion object {
        val TAG = "ScoresActivity"

        fun newIntent(context: Context) = Intent(context, ScoresActivity::class.java)
    }

}