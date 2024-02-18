package com.umc.sculptor.api

import com.umc.sculptor.data.model.remote.login.LoginDto
import com.umc.sculptor.data.model.remote.login.LogoutDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface LoginService {
    @GET("/user/login")
    fun kakaoLogin(@Header("Authorization") authorization: String  ): Call<LoginDto>

    @GET("/user-logout")
    fun logout(@Header("Cookie") authorization: String): Call<LogoutDto>
}