package com.capjjang.rightnow.api

import retrofit2.Call
import retrofit2.http.GET


interface StoreService {
    @GET("/store/users/stones")
    fun getMyStones()


//    @GET("/home")
//    fun test(): Call<>
}