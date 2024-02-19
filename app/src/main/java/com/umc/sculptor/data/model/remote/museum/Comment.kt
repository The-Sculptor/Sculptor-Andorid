package com.umc.sculptor.data.model.remote.museum


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    @SerialName("content")
    val content: String?,
    @SerialName("id")
    val id: String,
    @SerialName("isLike")
    var isLike: Boolean,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("writeAt")
    val writeAt: String,
    @SerialName("writerId")
    val writerId: String,
    @SerialName("writerNickname")
    val writerNickname: String,
    @SerialName("writerProfileImage")
    val writerProfileImage: String
)