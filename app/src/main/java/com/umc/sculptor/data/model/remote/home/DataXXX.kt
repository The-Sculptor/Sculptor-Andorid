package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXX(
    @SerialName("fromUser")
    val fromUser: String,
    @SerialName("isFollowing")
    val isFollowing: Boolean,
    @SerialName("toUser")
    val toUser: String
)