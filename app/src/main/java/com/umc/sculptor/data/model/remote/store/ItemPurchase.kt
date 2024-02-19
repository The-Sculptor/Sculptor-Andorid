package com.umc.sculptor.data.model.remote.store


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemPurchase(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: DataXXXXXXX,
    @SerialName("message")
    val message: String
)