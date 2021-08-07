package com.pilotflyingj.codechallenge.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class SpaceAvailability (
    @SerialName("LocationID")
    val locationID: Long,

    @SerialName("ItemID")
    val itemID: Long,

    @SerialName("Description")
    val description: String,

    @SerialName("Total")
    val total: Long,

    @SerialName("Booked")
    val booked: Long,

    @SerialName("Available")
    val available: Long,

    @SerialName("Price")
    val price: Long
)
