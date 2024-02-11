package com.capjjang.rightnow.api

import com.umc.sculptor.data.model.dto.CreateStoneRequestDto
import com.umc.sculptor.data.model.dto.CreateStoneResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface WorkshopService {
    @POST("/workplace/create")
    fun createStone(@Header("Cookie") accessToken: String, @Body createStoneRequestDto: CreateStoneRequestDto): Call<CreateStoneResponseDto>

}