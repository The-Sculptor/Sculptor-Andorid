package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXXXX(
    @SerialName("stoneId")
    val stoneId: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("isLike")
    val isLike: Boolean
)