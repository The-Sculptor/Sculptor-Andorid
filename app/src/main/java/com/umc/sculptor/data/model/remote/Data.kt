package com.umc.sculptor.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("id")
    val id: String,
    @SerialName("isFollowing")
    val isFollowing: Boolean,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("stone")
    val stone: Stone?
)