package com.capjjang.rightnow.api



import com.umc.sculptor.api.sculptStone
import com.umc.sculptor.data.model.remote.getAllAchieves
import com.umc.sculptor.data.model.remote.getOneStone
import com.umc.sculptor.data.model.remote.getStones
import com.umc.sculptor.data.model.dto.CreateStoneRequestDto
import com.umc.sculptor.data.model.dto.CreateStoneResponseDto
import com.umc.sculptor.data.model.remote.login.LogoutDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
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
        @Header("Cookie") accessToken: String,
        @Path("stoneId") stoneId: String,
    ): Call<getOneStone>

    @GET("/workplace/stones/{stoneId}/achieves")
    fun getAllAchieves(
        @Header("Cookie") accessToken: String,
        @Path("stoneId") stoneId: String,
    ): Call<getAllAchieves>

    @POST("/stones/{stoneId}/sculpt")
    fun sculptStone(
        @Header("Content-Type") contentType: String ,
        @Header("Cookie") accessToken: String
    ): Call<sculptStone>

    @DELETE("/stones/{stoneId}/delete")
    fun deleteStone(
        @Header("Cookie") accessToken: String,
        @Path("stoneId") stoneId: String
    ): Call<LogoutDto>
}

