package com.nicholas.ocholla.nyc.schools.mvvm.hilt.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.model.School
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.data.repository.MainRepository
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.util.NetworkHelper
import com.nicholas.ocholla.nyc.schools.mvvm.hilt.util.Resource
import kotlinx.coroutines.launch

class SchoolsViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _schools = MutableLiveData<Resource<List<School>>>()
    val schools: LiveData<Resource<List<School>>>
        get() = _schools

    init {
        fetchSchools()
    }

    private fun fetchSchools() {
        viewModelScope.launch {
            _schools.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getSchools().let {
                    if (it.isSuccessful) {
                        _schools.postValue(Resource.success(it.body()))
                    } else _schools.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _schools.postValue(Resource.error("No internet connection", null))
        }
    }

}