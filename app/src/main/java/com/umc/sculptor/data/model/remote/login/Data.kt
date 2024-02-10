package com.umc.sculptor.data.model.remote.login


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("sessionId")
    val sessionId: String
)