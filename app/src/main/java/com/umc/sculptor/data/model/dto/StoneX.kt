package com.umc.sculptor.data.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoneX(
    @SerialName("achievementCounts")
    val achievementCounts: AchievementCounts,
    @SerialName("achievementRate")
    val achievementRate: Int,
    @SerialName("category")
    val category: String,
    @SerialName("dDay")
    val dDay: String,
    @SerialName("goal")
    val goal: String,
    @SerialName("id")
    val id: String,
    @SerialName("isLike")
    val isLike: Boolean,
    @SerialName("isRepresent")
    val isRepresent: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("oneComment")
    val oneComment: String?,
    @SerialName("powder")
    val powder: Int,
    @SerialName("startDate")
    val startDate: String
)