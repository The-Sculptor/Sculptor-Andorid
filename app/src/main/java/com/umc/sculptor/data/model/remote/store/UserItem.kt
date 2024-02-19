package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItem(
    @SerialName("itemId")
    val itemId: String,
    @SerialName("itemName")
    val itemName: String,
    @SerialName("itemPrice")
    val itemPrice: Int,
    val isSelected: Boolean = false
)