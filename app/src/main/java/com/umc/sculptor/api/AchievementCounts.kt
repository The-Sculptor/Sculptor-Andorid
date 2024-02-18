package com.umc.sculptor.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AchievementCounts(
    @SerialName("A")
    val a: Int,
    @SerialName("B")
    val b: Int,
    @SerialName("C")
    val c: Int
)