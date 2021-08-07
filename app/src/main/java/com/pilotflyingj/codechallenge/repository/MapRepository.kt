package com.pilotflyingj.codechallenge.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.pilotflyingj.codechallenge.network.RetrofitServiceProvider
import com.pilotflyingj.codechallenge.network.models.ApiSite
import com.pilotflyingj.codechallenge.network.models.Constants
import com.pilotflyingj.codechallenge.repository.models.Site
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapRepository @Inject constructor(private val service: RetrofitServiceProvider) {
    // pull the service in, run any business logic through here so that the view model is
    // simply requesting data at this point

    //Using Dispatchers.IO since this is not a CPU intensive task. Otherwise I would use Default
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val _apiResponse= MutableLiveData<List<Site>>()
    val apiResponse: LiveData<List<Site>> = _apiResponse
    //Add repository live data

    fun getSiteInfo(){
        val siteList = ArrayList<Site>()
        coroutineScope.launch{
            val response = service.getSites().getSitesAsync()
            if(response.isSuccessful){
                response.body()?.forEach {
                    println(it)
                    siteList.add(Site(name = it.storeName, location = LatLng(it.latitude, it.longitude)))
                }
                _apiResponse.postValue(siteList)
            }

        }
    }
}