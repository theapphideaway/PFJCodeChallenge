package com.pilotflyingj.codechallenge.network

import com.pilotflyingj.codechallenge.network.models.ApiSite
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {
    // TODO: define the API endpoint here, use the models in network layer with Kotlin Serialization
    @GET("locations.json")
    suspend fun getSitesAsync(): Response<List<ApiSite>>
}