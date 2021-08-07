package com.pilotflyingj.codechallenge.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Test(val sites: List<ApiSite>)
