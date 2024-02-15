package com.capjjang.rightnow.api

import com.umc.sculptor.data.model.remote.store.UserStones
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface StoreService {
    @GET("/store/stones")
    fun getMyStones(@Header("Cookie") accessToken: String): Call<UserStones>

//    @GET("/home")
//    fun test(): Call<>
}