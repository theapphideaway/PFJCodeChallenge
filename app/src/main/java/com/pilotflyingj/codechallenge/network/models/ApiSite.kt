package com.pilotflyingj.codechallenge.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ApiSite (
    @SerialName("StoreNo")
    val storeNo: Long,

    @SerialName("StoreName")
    val storeName: String,

    @SerialName("City")
    val city: String,

    @SerialName("State")
    val state: String,

    @SerialName("Address")
    val address: String,

    @SerialName("AddressTwo")
    val addressTwo: JsonObject? = null,

    @SerialName("ZipCode")
    val zipCode: String,

    @SerialName("Country")
    val country: String,

    @SerialName("Interstate")
    val interstate: String,

    @SerialName("StoreFrontBrand")
    val storeFrontBrand: JsonObject? = null,

    @SerialName("Phone")
    val phone: String,

    @SerialName("Area")
    val area: JsonObject? = null,

    @SerialName("StoreType")
    val storeType: JsonObject? = null,

    @SerialName("Latitude")
    val latitude: Double,

    @SerialName("Longitude")
    val longitude: Double,

    @SerialName("SpaceAvailability")
    val spaceAvailability: List<SpaceAvailability>
)
