package com.capjjang.rightnow.api

import com.umc.sculptor.data.model.remote.FollowingStoneDetail
import com.umc.sculptor.data.model.remote.home.FollowResponseDto
import com.umc.sculptor.data.model.remote.home.FollowingsStone
import com.umc.sculptor.data.model.remote.home.MyPageResonseDto
import com.umc.sculptor.data.model.remote.home.MyRepresentStone
import com.umc.sculptor.data.model.remote.home.PressLike
import com.umc.sculptor.data.model.remote.home.PrivateDto
import com.umc.sculptor.data.model.remote.home.PrivateRequestDto
import com.umc.sculptor.data.model.remote.home.ResearchResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface HomeService {
    @GET("/followings/stones")
    fun getFollowingsStones(@Header("Cookie") accessToken: String): Call<FollowingsStone>

    @GET("/users/search")
    fun search(@Header("Cookie") accessToken: String, @Query("name") name: String): Call<ResearchResponseDto>


    @GET("/mypage")
    fun getMypage(@Header("Cookie") accessToken: String):Call<MyPageResonseDto>

    @GET("/users/represent-stone")
    fun getMyRepresentStone(@Header("Cookie") accessToken: String) : Call<MyRepresentStone>

    @POST("/{stoneId}/like")
    fun like(@Header("Cookie") accessToken: String,  @Path("stoneId") stoneId: String) : Call<PressLike>

    @GET("/followings/{userId}/represent-stone")
    fun getFriendStone(@Header("Cookie") accessToken: String, @Path("userId") userId: String) : Call<FollowingStoneDetail>

    @PATCH("/mypage/private")
    fun changePrivate(@Header("Cookie") accessToken: String, @Body privateDto: PrivateRequestDto):Call<PrivateDto>

    @POST("/follow")
    fun follow(@Header("Cookie") accessToken: String, @Query("followId") id: String):Call<FollowResponseDto>
}