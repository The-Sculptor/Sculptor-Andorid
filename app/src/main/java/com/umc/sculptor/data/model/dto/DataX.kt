package com.umc.sculptor.data.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataX(
    @SerialName("comments")
    val comments: List<Comment>,
    @SerialName("stone")
    val stone: StoneX
)