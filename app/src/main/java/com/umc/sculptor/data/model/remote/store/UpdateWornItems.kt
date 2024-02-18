package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateWornItems(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXXX,
    @SerialName("message")
    val message: String
)