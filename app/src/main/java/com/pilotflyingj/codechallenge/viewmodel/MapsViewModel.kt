package com.pilotflyingj.codechallenge.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pilotflyingj.codechallenge.network.RetrofitServiceProvider
import com.pilotflyingj.codechallenge.network.models.Constants
import com.pilotflyingj.codechallenge.repository.MapRepository
import com.pilotflyingj.codechallenge.repository.models.Site
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


//ViewModelInject is Deprecated
@HiltViewModel
class MapsViewModel @Inject constructor(private val mapRepository: MapRepository) : ViewModel() {
    // call the repository's method to get location list. Dont export the API model to the view
    private val _siteResponse= MutableLiveData<List<Site>>()
    val siteResponse: LiveData<List<Site>> = _siteResponse

    fun getSites(){
        //Observers will automatically removed by the framework when the lifecycleowner is destroyed.
        mapRepository.apiResponse.observeForever {
            _siteResponse.postValue(it)
        }
        mapRepository.getSiteInfo()
    }
}