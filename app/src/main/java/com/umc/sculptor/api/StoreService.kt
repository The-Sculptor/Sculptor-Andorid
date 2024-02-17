package com.capjjang.rightnow.api

import com.umc.sculptor.data.model.remote.store.StoreItems
import com.umc.sculptor.data.model.remote.store.UserMoney
import com.umc.sculptor.data.model.remote.store.UserStones
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH


interface StoreService {
    @GET("/store/stones")//나의 조각상 조회
    fun getMyStones(@Header("Cookie") accessToken: String): Call<UserStones>

    @GET("/store/items")//상점 물건 조회
    fun getStoreItems(@Header("Cookie") accessToken: String): Call<StoreItems>


    @GET("/store/users/money")//돈 조회
    fun getMoney(@Header("Cookie") accessToken: String): Call<UserMoney>


    @PATCH("/store/stones/{stoneId}/items")//물건 착용
    fun updateItemWear()
                //(@Header("Cookie") accessToken: String, @Query("stoneId") id: String): Call<ItemWearResponseDto>

//    @GET("/home")
//    fun test(): Call<>
}