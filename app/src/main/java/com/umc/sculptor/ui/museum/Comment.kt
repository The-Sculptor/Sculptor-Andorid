package com.umc.sculptor.ui.museum

data class Comment(
    val profileImage: Int?=null,
    val nickname: String?="",
    val comment: String?="",
    var heartImg: Boolean?=null
)
