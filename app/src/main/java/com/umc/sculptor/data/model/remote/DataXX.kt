package com.umc.sculptor.data.model.remote


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