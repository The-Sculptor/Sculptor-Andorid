package com.umc.sculptor.data.model.remote


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