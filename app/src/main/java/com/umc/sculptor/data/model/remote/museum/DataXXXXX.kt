package com.umc.sculptor.data.model.remote.museum


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXXXX(
    @SerialName("commentId")
    val commentId: String,
    @SerialName("isLike")
    val isLike: Boolean
)