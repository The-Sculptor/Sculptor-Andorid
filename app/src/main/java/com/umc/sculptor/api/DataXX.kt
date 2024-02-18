package com.umc.sculptor.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXX(
    @SerialName("achievementCounts")
    val achievementCounts: AchievementCounts,
    @SerialName("achieves")
    val achieves: List<Achieve>,
    @SerialName("stoneId")
    val stoneId: String
)