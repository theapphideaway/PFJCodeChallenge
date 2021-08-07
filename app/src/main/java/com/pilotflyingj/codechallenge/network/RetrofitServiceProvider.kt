package com.pilotflyingj.codechallenge.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pilotflyingj.codechallenge.network.models.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitServiceProvider @Inject constructor(val okHttpProvider: OkHttpProvider
) {
    // create the API service lazily and retain in memory
    fun getSites(): LocationService {
        return okHttpProvider.retrofit.create(LocationService::class.java)
    }
}