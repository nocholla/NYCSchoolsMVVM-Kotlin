package com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model

import com.squareup.moshi.Json

/**
 * Score data class.
 */
data class Score(
    @Json(name = "dbn")
    var databaseName: String,
    @Json(name = "school_name")
    var schoolName: String,
    @Json(name = "num_of_sat_test_takers")
    var numberOfSatTestTakers: String,
    @Json(name = "sat_critical_reading_avg_score")
    var satCriticalReadingAverageScore: String,
    @Json(name = "sat_math_avg_score")
    var satMathAvgScore: String,
    @Json(name = "sat_writing_avg_score")
    var satWritingAverageScore: String
)
