package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoneItem(
    @SerialName("isWorn")
    val isWorn: Boolean,
    @SerialName("itemId")
    val itemId: String
)