package com.capjjang.rightnow.api

import com.umc.sculptor.data.model.dto.MuseumDetail
import com.umc.sculptor.data.model.remote.login.LogoutDto
import com.umc.sculptor.data.model.remote.museum.CommentLike
import com.umc.sculptor.data.model.remote.museum.CommentLikeDto
import com.umc.sculptor.data.model.remote.museum.CommentResponse
import com.umc.sculptor.data.model.remote.museum.Comments
import com.umc.sculptor.data.model.remote.museum.EditProfileDto
import com.umc.sculptor.data.model.remote.museum.EditReqeustDto
import com.umc.sculptor.data.model.remote.museum.EditStonesDto
import com.umc.sculptor.data.model.remote.museum.EditUserDto
import com.umc.sculptor.data.model.remote.museum.Museum
import com.umc.sculptor.data.model.remote.museum.RepresentResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
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

    @GET("/museum/stones/{stoneId}")
    fun getMuseumDetail(@Header("Cookie") accessToken: String,@Path("stoneId") stoneId: String): Call<MuseumDetail>

    @GET("/museum/stones/{stoneId}/comments")
    fun getComments(@Header("Cookie") accessToken: String,@Path("stoneId") stoneId: String): Call<Comments>

    @PATCH("/user/represent-stone/{stoneId}")
    fun representStone(@Header("Cookie") accessToken: String,@Path("stoneId") stoneId: String): Call<RepresentResponseDto>

    @PATCH("/museum/comments/{commentId}/like")
    fun changeCommentLike(@Header("Cookie") accessToken: String,@Path("commentId") commentId: String) :Call<CommentLike>

    @POST("/museum/stones/{stoneId}/comments")
    fun comment(@Header("Cookie") accessToken: String,@Path("stoneId") commentId: String, @Body content : String) :Call<CommentResponse>
}