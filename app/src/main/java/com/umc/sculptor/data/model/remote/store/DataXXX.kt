package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXX(
    @SerialName("stoneId")
    val stoneId: String,
    @SerialName("stoneItems")
    val stoneItems: List<StoneItem>
)