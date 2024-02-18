package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXX(
    @SerialName("totalPowder")
    val totalPowder: Int,
    @SerialName("userId")
    val userId: String
)