package com.umc.sculptor.data.model.remote.museum


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXXXXX(
    @SerialName("content")
    val content: String,
    @SerialName("id")
    val id: String,
    @SerialName("stoneId")
    val stoneId: String,
    @SerialName("writeAt")
    val writeAt: String,
    @SerialName("writerId")
    val writerId: String
)