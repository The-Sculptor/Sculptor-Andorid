package com.umc.sculptor.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SculptorRequest(
    @SerialName("achieveStatus")
    val achieveStatus: String,
    @SerialName("date")
    val date: String
)