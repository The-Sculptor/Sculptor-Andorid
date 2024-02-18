package com.capjjang.rightnow.api

import com.umc.sculptor.data.model.remote.login.LoginDto
import com.umc.sculptor.data.model.remote.login.LogoutDto
import com.umc.sculptor.data.model.remote.museum.EditProfileDto
import com.umc.sculptor.data.model.remote.museum.EditReqeustDto
import com.umc.sculptor.data.model.remote.museum.EditStonesDto
import com.umc.sculptor.data.model.remote.museum.EditUserDto
import com.umc.sculptor.data.model.remote.museum.Museum
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path


interface MuseumService {
    @GET("/museum/users/{ownerId}")
    fun getMuseum(@Header("Cookie") accessToken: String, @Path("ownerId") ownerId: String): Call<Museum>

    @GET("/museum/profile/user")
    fun getUser(@Header("Cookie") accessToken: String): Call<EditProfileDto>


    @GET("/museum/profile/stones")
    fun getStones(@Header("Cookie") accessToken: String): Call<EditStonesDto>


    @PATCH("/museum/profile/user")
    fun editProfile(@Header("Cookie") accessToken: String, @Body request: EditReqeustDto): Call<EditUserDto>


    @DELETE("/museum/profile/stones/{stoneId}")
    fun deleteStone(@Header("Cookie") accessToken: String, @Path("stoneId") stoneId: String): Call<LogoutDto>
}