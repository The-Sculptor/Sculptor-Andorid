package com.umc.sculptor.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class sculptStone(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXXX,
    @SerialName("message")
    val message: String
)