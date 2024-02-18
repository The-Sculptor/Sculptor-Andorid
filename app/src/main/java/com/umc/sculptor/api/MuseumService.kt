package com.capjjang.rightnow.api

import com.umc.sculptor.data.model.remote.museum.Data
import com.umc.sculptor.data.model.remote.museum.Museum
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface MuseumService {
    @GET("/museum/users/{ownerId}")
    fun getMuseum(@Header("Cookie") accessToken: String, @Path("ownerId") ownerId: String): Call<Museum>
}