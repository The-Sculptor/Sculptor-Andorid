package com.umc.sculptor.data.model.remote


import com.umc.sculptor.ui.workshop.StoneStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataX(
    @SerialName("achRate")
    val achRate: Int,
    @SerialName("category")
    val category: String,
    @SerialName("dday")
    val dday: String,
    @SerialName("likes")
    val likes: Int,
    @SerialName("powder")
    val powder: Int,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("stoneGoal")
    val stoneGoal: String,
    @SerialName("stoneId")
    val stoneId: String,
    @SerialName("stoneName")
    val stoneName: String,
    @SerialName("stoneStatus")
    val stoneStatus: StoneStatus
)