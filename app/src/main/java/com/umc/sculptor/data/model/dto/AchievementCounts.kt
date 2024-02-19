package com.umc.sculptor.data.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AchievementCounts(
    @SerialName("A")
    val A: String,
    @SerialName("B")
    val B: String,
    @SerialName("C")
    val C: String
)