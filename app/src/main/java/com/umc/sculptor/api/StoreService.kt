package com.capjjang.rightnow.api

import com.umc.sculptor.data.model.remote.store.Basket
import com.umc.sculptor.data.model.remote.store.PurchasedItems
import com.umc.sculptor.data.model.remote.store.StoreItems
import com.umc.sculptor.data.model.remote.store.UpdateWornItems
import com.umc.sculptor.data.model.remote.store.UserMoney
import com.umc.sculptor.data.model.remote.store.UserStones
import com.umc.sculptor.data.model.remote.store.WornItems
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface StoreService {
    @GET("/store/stones")//나의 조각상 조회
    fun getMyStones(@Header("Cookie") accessToken: String): Call<UserStones>

    @GET("/store/items")//상점 물건 조회
    fun getStoreItems(@Header("Cookie") accessToken: String): Call<StoreItems>

    @GET("/store/users/money")//돈 조회
    fun getMoney(@Header("Cookie") accessToken: String): Call<UserMoney>



    @PATCH("/store/stones/{stoneId}/items")//물건 착용
    fun updateWornItem(@Header("Cookie") accessToken: String, @Path("stoneId") stoneid: String): Call<UpdateWornItems>


    @GET("store/stones/{stoneId}")//착용중 상품 조회
    fun getWornItems(@Header("Cookie") accessToken: String, @Path("stoneId") stoneid: String): Call<WornItems>

    @POST("/store/stones/{stoneId}/basket")//구매할 아이템 조회
    fun getBasket(@Header("Cookie") accessToken: String, @Path("stoneId") stoneid: String, @Body requestBody: RequestBody): Call<Basket>

//@Query("itemIds") itemIds: List<String>
    @GET("/store/users/items")//구매한 아이템 조회
    fun getPurchasedItems(@Header("Cookie") accessToken: String): Call<PurchasedItems>


//    @GET("/home")
//    fun test(): Call<>
}