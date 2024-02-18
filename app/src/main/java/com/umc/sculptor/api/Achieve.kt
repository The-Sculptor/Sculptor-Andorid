package com.umc.sculptor.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Achieve(
    @SerialName("achieveId")
    val achieveId: String,
    @SerialName("achieveStatus")
    val achieveStatus: String,
    @SerialName("date")
    val date: String
)