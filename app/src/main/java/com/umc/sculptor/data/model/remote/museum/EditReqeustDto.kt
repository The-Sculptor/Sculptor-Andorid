package com.umc.sculptor.data.model.remote.museum

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditReqeustDto(
    @SerialName("nickname")
    val nickname: String?,
    @SerialName("profileImage")
    val profileImage: String?,
    @SerialName("introduction")
    val introduction: String?
)