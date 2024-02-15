package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrivateDto(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXXXXXX,
    @SerialName("message")
    val message: String
)