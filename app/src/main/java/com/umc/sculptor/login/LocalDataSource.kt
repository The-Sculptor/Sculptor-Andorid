package com.umc.sculptor.login

import android.content.Context
import android.content.SharedPreferences

object LocalDataSource {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    fun setAccessToken(token: String) {
        sharedPreferences.edit().putString("access_token", token).apply()
    }
}