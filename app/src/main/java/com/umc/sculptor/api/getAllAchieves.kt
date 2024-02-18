package com.umc.sculptor.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class getAllAchieves(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXX,
    @SerialName("message")
    val message: String
)