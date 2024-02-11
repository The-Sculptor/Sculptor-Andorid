package com.umc.sculptor.api

import com.umc.sculptor.data.model.remote.login.LoginDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LoginService {
    @GET("login/oauth2/code/kakao")
    fun kakaoLogin(@Query("code") code: String, @Query("state") state: String): Call<LoginDto>
}