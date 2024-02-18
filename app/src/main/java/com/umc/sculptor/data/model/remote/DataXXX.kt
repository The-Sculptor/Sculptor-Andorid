package com.umc.sculptor.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXX(
    @SerialName("category")
    val category: String,
    @SerialName("dday")
    val dday: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("stoneGoal")
    val stoneGoal: String,
    @SerialName("stoneId")
    val stoneId: String,
    @SerialName("stoneName")
    val stoneName: String,
    @SerialName("userId")
    val userId: String
)