package com.umc.sculptor.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class getOneStone(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataX,
    @SerialName("message")
    val message: String
)