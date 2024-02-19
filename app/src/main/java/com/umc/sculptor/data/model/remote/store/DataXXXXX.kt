package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXXXX(
    @SerialName("items")
    val items: List<ItemX>,
    @SerialName("stoneId")
    val stoneId: String,
    @SerialName("totalPrice")
    val totalPrice: Int
)