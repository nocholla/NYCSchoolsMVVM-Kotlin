package com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.api

import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model.School
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model.Score
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    // Get Schools
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): Response<List<School>>

    // Get Scores
    @GET("resource/f9bf-2cp4.json")
    suspend fun getScores(): Response<List<Score>>

}