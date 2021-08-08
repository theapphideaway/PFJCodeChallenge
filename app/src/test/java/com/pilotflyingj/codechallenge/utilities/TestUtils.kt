package com.pilotflyingj.codechallenge.utilities

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pilotflyingj.codechallenge.network.LocationService
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

fun createLocationService(baseUrl: HttpUrl): LocationService {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(contentType))
        .baseUrl(baseUrl)
        .build()
        .create(LocationService::class.java)
}

fun MockWebServer.enqueueResponse(fileName: String, code: Int = 200) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}