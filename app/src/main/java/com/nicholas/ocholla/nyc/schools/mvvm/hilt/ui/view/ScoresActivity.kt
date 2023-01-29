package com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.view

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.R

class ScoresActivity : AppCompatActivity() {

    // Orientation
    private val isTablet: Boolean
        get() = (this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        // Hide Toolbar
        supportActionBar?.hide()

        // Orientation Check
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        // Widgets
        val backBtn = findViewById<ImageButton>(R.id.ib_close)


        backBtn.setOnClickListener {
            finish()
        }

    }

    companion object {
        val TAG = "ScoresActivity"

        fun newIntent(context: Context) = Intent(context, ScoresActivity::class.java)
    }

}