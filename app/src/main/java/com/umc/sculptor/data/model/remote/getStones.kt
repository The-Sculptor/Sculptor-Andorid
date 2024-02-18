package com.umc.sculptor.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class getStones(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<DataXXX>,
    @SerialName("message")
    val message: String
)