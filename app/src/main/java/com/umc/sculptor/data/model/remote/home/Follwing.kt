package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class Follwing(
    @SerialName("id")
    val id: String,
    @SerialName("isLike")
    val isLike: Boolean,
    @SerialName("like")
    val like: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("stoneDDay")
    val stoneDDay: String,
    @SerialName("stoneGoal")
    val stoneGoal: String,
    @SerialName("stoneName")
    val stoneName: String
)

