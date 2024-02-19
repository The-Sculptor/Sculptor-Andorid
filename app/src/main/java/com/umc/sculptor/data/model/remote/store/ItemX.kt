package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemX(
    @SerialName("id")
    val id: String,
    @SerialName("isPurchased")
    val isPurchased: Boolean,
    @SerialName("price")
    val price: Int,
    var isChecked:Boolean = false
)