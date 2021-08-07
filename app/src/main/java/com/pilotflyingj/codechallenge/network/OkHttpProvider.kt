package com.pilotflyingj.codechallenge.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pilotflyingj.codechallenge.network.models.Constants
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalSerializationApi::class)
@Singleton
class OkHttpProvider @Inject constructor(){
    val retrofit: Retrofit

    init {
        val contentType = "application/json".toMediaType()
        val interceptor = HttpLoggingInterceptor().
        setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BaseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
    }
}