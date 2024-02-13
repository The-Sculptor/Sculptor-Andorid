package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXXX(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("stone")
    val stone: Stone
)