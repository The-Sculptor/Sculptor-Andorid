package com.umc.sculptor.data.model.remote.museum


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("followerCount")
    val followerCount: Int,
    @SerialName("followingCount")
    val followingCount: Int,
    @SerialName("id")
    val id: String,
    @SerialName("introduction")
    val introduction: String,
    @SerialName("isFollowing")
    val isFollowing: Boolean,
    @SerialName("isOwner")
    val isOwner: Boolean,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("stoneCount")
    val stoneCount: Int,
    @SerialName("stones")
    val stones: List<Stone>
)