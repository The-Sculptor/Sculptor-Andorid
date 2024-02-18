package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PressLike(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXXXXX,
    @SerialName("message")
    val message: String
)