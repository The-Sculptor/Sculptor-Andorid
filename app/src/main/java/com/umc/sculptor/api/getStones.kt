package com.umc.sculptor.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class getStones(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("message")
    val message: String
)