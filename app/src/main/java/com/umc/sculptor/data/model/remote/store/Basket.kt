package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Basket(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXXXXX,
    @SerialName("message")
    val message: String
)