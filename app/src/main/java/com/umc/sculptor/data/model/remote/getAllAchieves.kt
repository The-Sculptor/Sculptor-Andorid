package com.umc.sculptor.data.model.remote


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