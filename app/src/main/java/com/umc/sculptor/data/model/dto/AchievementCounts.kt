package com.umc.sculptor.data.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AchievementCounts
(
    @SerialName("A")
    val A: Int,
    @SerialName("B")
    val B: Int,
    @SerialName("C")
    val C: Int
)
