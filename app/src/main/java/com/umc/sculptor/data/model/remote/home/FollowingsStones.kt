package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowingsStones(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("message")
    val message: String
)