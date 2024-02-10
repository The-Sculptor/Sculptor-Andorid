package com.umc.sculptor.apiManager

import com.capjjang.rightnow.api.HomeService
import com.capjjang.rightnow.api.MuseumService
import com.capjjang.rightnow.api.StoreService
import com.capjjang.rightnow.api.WorkshopService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.umc.sculptor.BuildConfig
import com.umc.sculptor.api.LoginService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL = BuildConfig.AUTH_BASE_URL

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val loginService = ApiFactory.create<LoginService>()
    val homeService = ApiFactory.create<HomeService>()
    val museumService = ApiFactory.create<MuseumService>()
    val storeService = ApiFactory.create<StoreService>()
    val workshopService = ApiFactory.create<WorkshopService>()
}
