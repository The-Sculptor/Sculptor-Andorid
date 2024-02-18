package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXXX(
    @SerialName("stoneItems")
    val stoneItems: List<StoneItemX>
)