package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXX(
    @SerialName("is_public")
    val isPublic: Boolean,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profile_image")
    val profileImage: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("user_name")
    val userName: String
)