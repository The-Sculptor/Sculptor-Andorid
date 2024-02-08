package com.umc.sculptor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.umc.sculptor.data.model.dto.LoginViewModel
import com.umc.sculptor.databinding.ActiviyLoginBinding
import com.umc.sculptor.login.KakaoLoginService
import com.umc.sculptor.login.LocalDataSource
import com.umc.sculptor.login.LoginViewModelFactory


class LoginActivity:  AppCompatActivity() {

    lateinit var binding: ActiviyLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActiviyLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var keyHash = Utility.getKeyHash(this)
        Log.d("키해시", " $keyHash")

        KakaoSdk.init(this, BuildConfig.NATIVE_APP_KEY)



        binding.appCompatImageView2.setOnClickListener {
            LocalDataSource.init(this)
            val kakaoLoginService = KakaoLoginService(this)
            val viewModelFactory = LoginViewModelFactory(kakaoLoginService)
            val model: LoginViewModel = ViewModelProvider(this@LoginActivity, viewModelFactory).get(LoginViewModel::class.java)
            model.kakaoLogin()
        }
    }




}