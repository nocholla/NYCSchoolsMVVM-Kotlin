package com.nicholas.ocholla.nyc.schools.mvvm.hilt.util

import android.view.View

inline fun View.addDebouncedClickListener(crossinline onClick: () -> Unit) {
    setOnClickListener(object : DebouncedClickListener() {
        override fun onClicked(v: View?) {
            onClick()
        }
    })
}