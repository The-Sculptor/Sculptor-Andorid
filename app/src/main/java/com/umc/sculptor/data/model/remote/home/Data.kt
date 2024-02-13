package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("achieveRate")
    val achieveRate: Int,
    @SerialName("id")
    val id: String,
    @SerialName("isLike")
    var isLike: Boolean,
    @SerialName("like")
    var like: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("stoneDDay")
    val stoneDDay: String,
    @SerialName("stoneGoal")
    val stoneGoal: String,
    @SerialName("stoneId")
    val stoneId: String,
    @SerialName("stoneName")
    val stoneName: String
)