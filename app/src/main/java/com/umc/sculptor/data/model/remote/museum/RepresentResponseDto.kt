package com.umc.sculptor.data.model.remote.museum


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepresentResponseDto(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXXXXX,
    @SerialName("message")
    val message: String
)