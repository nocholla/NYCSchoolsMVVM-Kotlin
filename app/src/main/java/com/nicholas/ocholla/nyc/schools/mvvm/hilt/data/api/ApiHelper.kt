package com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.api

import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model.School
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model.Score
import retrofit2.Response

interface ApiHelper {

    suspend fun getSchools(): Response<List<School>>

    suspend fun getScores(): Response<List<Score>>

}