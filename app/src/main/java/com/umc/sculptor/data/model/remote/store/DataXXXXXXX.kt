package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXXXXXX(
    @SerialName("items")
    val items: List<ItemXX>,
    @SerialName("stoneId")
    val stoneId: String
)