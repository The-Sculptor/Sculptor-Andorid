package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PurChasedItemsDto(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXXXXXXXX,
    @SerialName("message")
    val message: String
)