package com.umc.sculptor.data.model.remote.login


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoutDto(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val data: Data?, // Nullable로 변경됨
    @SerialName("message")
    val message: String
)