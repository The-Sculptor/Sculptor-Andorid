package com.umc.sculptor.data.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MuseumDetail(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataX,
    @SerialName("message")
    val message: String
)