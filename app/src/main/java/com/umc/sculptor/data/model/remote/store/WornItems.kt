package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WornItems(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXXXX,
    @SerialName("message")
    val message: String
)