package com.umc.sculptor.data.model.remote.museum


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoneX(
    @SerialName("dDay")
    val dDay: String,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)