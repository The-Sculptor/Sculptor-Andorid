package com.umc.sculptor

import android.content.Intent
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class LoginActivity:  AppCompatActivity() {

    lateinit var binding: ActiviyLoginBinding
    val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActiviyLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var keyHash = Utility.getKeyHash(this)
        Log.d("키해시", " $keyHash")

        KakaoSdk.init(this, BuildConfig.NATIVE_APP_KEY)

        LocalDataSource.init(this)


        binding.appCompatImageView2.setOnClickListener {
            coroutineScope.launch {
                LocalDataSource.init(this@LoginActivity)
                val kakaoLoginService = KakaoLoginService(this@LoginActivity)
                val viewModelFactory = LoginViewModelFactory(kakaoLoginService)
                val model: LoginViewModel = ViewModelProvider(this@LoginActivity, viewModelFactory).get(LoginViewModel::class.java)
                model.kakaoLogin()

                // Kakao 로그인 시도가 완료될 때까지 대기
                model.isKakaoLogin.collect { isKakaoLogin ->
                    if (isKakaoLogin) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        coroutineScope.cancel()
                    }
                }
            }
        }
    }




}