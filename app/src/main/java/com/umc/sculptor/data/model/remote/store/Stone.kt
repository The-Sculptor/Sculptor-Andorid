package com.umc.sculptor.data.model.remote.store
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stone(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("powder")
    val powder: Int
)