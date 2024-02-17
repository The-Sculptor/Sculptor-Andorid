package com.capjjang.rightnow.api

import com.umc.sculptor.data.model.remote.store.StoreItems
import com.umc.sculptor.data.model.remote.store.UserStones
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Query


interface StoreService {
    @GET("/store/stones")
    fun getMyStones(@Header("Cookie") accessToken: String): Call<UserStones>

    @GET("/store/items")
    fun getStoreItems(@Header("Cookie") accessToken: String): Call<StoreItems>




    @PATCH("/store/stones/{stoneId}/items")
    fun updateItemWear()
                //(@Header("Cookie") accessToken: String, @Query("stoneId") id: String): Call<ItemWearResponseDto>

//    @GET("/home")
//    fun test(): Call<>
}