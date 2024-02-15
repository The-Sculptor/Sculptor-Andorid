package com.umc.sculptor.data.model.remote.home


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataXXXXXX(
    @SerialName("updatedFields")
    val updatedFields: PrivateRequestDto
)