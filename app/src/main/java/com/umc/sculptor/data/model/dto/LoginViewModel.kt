package com.umc.sculptor.data.model.dto

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.umc.sculptor.login.KakaoLoginCallback
import com.umc.sculptor.login.KakaoLoginService
import com.umc.sculptor.login.LocalDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(private val kakaoLoginService: KakaoLoginService) : ViewModel() {
    private val _isKakaoLogin = MutableStateFlow(false)
    val isKakaoLogin = _isKakaoLogin.asStateFlow()

    val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        KakaoLoginCallback {
            _isKakaoLogin.value = true
            Timber.d("토큰!!!! $token")
            Log.d("소셜로그인", token.toString())
            if (token != null) {
                LocalDataSource.setAccessToken(token.accessToken)
            }
        }.handleResult(token, error)
    }

    fun kakaoLogin() = viewModelScope.launch {
        kakaoLoginService.startKakaoLogin(kakaoLoginCallback)
    }
}