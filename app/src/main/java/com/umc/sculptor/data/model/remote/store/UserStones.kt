package com.umc.sculptor.data.model.remote.store
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserStones(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<Stone>,
    @SerialName("message")
    val message: String
)