package com.umc.sculptor.data.model.remote.museum


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditUserDto(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXXX,
    @SerialName("message")
    val message: String
)