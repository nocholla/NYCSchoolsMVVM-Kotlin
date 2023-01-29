package com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.api

import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model.School
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model.Score
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getSchools(): Response<List<School>> = apiService.getSchools()

    override suspend fun getScores(): Response<List<Score>> = apiService.getScores()

}