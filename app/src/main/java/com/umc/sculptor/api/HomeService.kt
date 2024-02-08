package com.capjjang.rightnow.api

import com.umc.sculptor.BuildConfig
import com.umc.sculptor.data.model.remote.home.FollowingsStones
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface HomeService {
    @Headers("JSESSIONID=${BuildConfig.SESSION_ID}")
    @GET("/home")
    fun getHome(): Call<FollowingsStones>
}