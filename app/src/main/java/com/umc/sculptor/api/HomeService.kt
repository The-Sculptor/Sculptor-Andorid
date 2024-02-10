package com.capjjang.rightnow.api

import com.umc.sculptor.data.model.remote.home.FollowResponseDto
import com.umc.sculptor.data.model.remote.home.FollowingsStones
import com.umc.sculptor.data.model.remote.home.MyPageResonseDto
import com.umc.sculptor.data.model.remote.home.ResearchResponseDto
import com.umc.sculptor.ui.home.MyPageFragment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface HomeService {
    @GET("/followings/stones")
    fun getFollowingsStones(@Header("Cookie") accessToken: String): Call<FollowingsStones>

    @GET("/users/search")
    fun search(@Header("Cookie") accessToken: String, @Query("name") name: String): Call<ResearchResponseDto>


    @GET("/mypage")
    fun getMypage(@Header("Cookie") accessToken: String):Call<MyPageResonseDto>

    @POST("/follow")
    fun follow(@Header("Cookie") accessToken: String, @Query("followId") id: String):Call<FollowResponseDto>
}