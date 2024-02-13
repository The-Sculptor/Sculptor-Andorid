package com.umc.sculptor.data.model.remote


import com.umc.sculptor.data.model.remote.Data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowingStoneDetail(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: Data,
    @SerialName("message")
    val message: String
)