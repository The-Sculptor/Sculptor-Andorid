package com.umc.sculptor.api

import com.umc.sculptor.data.model.remote.login.LoginDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface LoginService {
    @GET("/user/login")
    fun kakaoLogin(@Header("Authorization") authorization: String  ): Call<LoginDto>
}