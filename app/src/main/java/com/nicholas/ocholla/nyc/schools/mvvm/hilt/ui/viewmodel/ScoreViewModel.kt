package com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model.Score
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.repository.MainRepository
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.util.NetworkHelper
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.util.Resource
import kotlinx.coroutines.launch

class ScoreViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _scores = MutableLiveData<Resource<List<Score>>>()
    val scores: LiveData<Resource<List<Score>>>
        get() = _scores

    init {
        fetchScores()
    }

    private fun fetchScores() {
        viewModelScope.launch {
            _scores.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getScores().let {
                    if (it.isSuccessful) {
                        _scores.postValue(Resource.success(it.body()))
                    } else _scores.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _scores.postValue(Resource.error("No internet connection", null))
        }
    }

}