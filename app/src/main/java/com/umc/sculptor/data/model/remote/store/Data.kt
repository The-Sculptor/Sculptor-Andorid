package com.umc.sculptor.data.model.remote.store
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("stones")
    val stones: List<Stone>
)