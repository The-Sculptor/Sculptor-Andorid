package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("follwings")
    val follwings: List<Follwing>,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("stoneDDay")
    val stoneDDay: String,
    @SerialName("stoneGoal")
    val stoneGoal: String,
    @SerialName("stoneName")
    val stoneName: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("userName")
    val userName: String
)