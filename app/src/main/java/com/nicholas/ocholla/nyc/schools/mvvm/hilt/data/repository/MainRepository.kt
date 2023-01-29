package com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.repository

import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getSchools() =  apiHelper.getSchools()

    suspend fun getScores() =  apiHelper.getScores()

}