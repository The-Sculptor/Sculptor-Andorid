package com.umc.sculptor.data.model.dto

data class Stone(
    var name: String,
    var category: Category? = null,
    var goal:String = "",
    var start_date: String = "",
    var dday:String="-66"
)
