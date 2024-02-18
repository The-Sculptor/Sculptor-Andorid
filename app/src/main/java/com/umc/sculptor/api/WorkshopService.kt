package com.capjjang.rightnow.api

import com.umc.sculptor.api.getAllAchieves
import com.umc.sculptor.api.getOneStone
import com.umc.sculptor.api.getStones
import com.umc.sculptor.data.model.dto.CreateStoneRequestDto
import com.umc.sculptor.data.model.dto.CreateStoneResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID


interface WorkshopService {
    @POST("/workplace/create")
    fun createStone(
        @Header("Cookie") accessToken: String ,
        @Body createStoneRequestDto: CreateStoneRequestDto
    ): Call<CreateStoneResponseDto>

    @GET("/stones")
    fun getStones(
    @Query("category") category: String,
    @Header("Cookie") accessToken: String
    ): Call<getStones>

    @GET("/stones/{stoneId}")
    fun getOneStone(
        @Header("Content-Type") contentType: String ,
        @Header("Cookie") accessToken: String
    ): Call<getOneStone>

    @GET("/workplace/stones/{stoneId}/achieves")
    fun getAllAchieves(
        @Header("Content-Type") contentType: String ,
        @Header("Cookie") accessToken: String
    ): Call<getAllAchieves>

}

