package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataX(
    @SerialName("id")
    val id: String,
    @SerialName("profileUrl")
    val profileUrl: String,
    @SerialName("username")
    val username: String
)