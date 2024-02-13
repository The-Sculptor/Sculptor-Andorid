package com.umc.sculptor.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stone(
    @SerialName("achievementRate")
    val achievementRate: Int,
    @SerialName("dDay")
    val dDay: String,
    @SerialName("goal")
    val goal: String,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("startDate")
    val startDate: String
)