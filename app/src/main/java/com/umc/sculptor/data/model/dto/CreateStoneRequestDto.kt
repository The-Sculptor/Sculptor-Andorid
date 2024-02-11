package com.umc.sculptor.data.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateStoneRequestDto(
    @SerialName("category")
    val category: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("stoneGoal")
    val stoneGoal: String,
    @SerialName("stoneName")
    val stoneName: String
)