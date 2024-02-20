package com.umc.sculptor.data.model.dto

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.data.model.remote.home.MyRepresentStone
import com.umc.sculptor.data.model.remote.login.LoginDto
import com.umc.sculptor.login.KakaoLoginCallback
import com.umc.sculptor.login.KakaoLoginService
import com.umc.sculptor.login.LocalDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class LoginViewModel(private val kakaoLoginService: KakaoLoginService) : ViewModel() {
    private val _isKakaoLogin = MutableStateFlow(false)
    val isKakaoLogin = _isKakaoLogin.asStateFlow()

    val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        KakaoLoginCallback {
            Timber.d("토큰!!!! $token")
            Log.d("소셜로그인", token.toString())
            val call: Call<LoginDto> = ServicePool.loginService.kakaoLogin(
                "Bearer " + (token?.accessToken
                    ?: "")
            )

            // 비동기적으로 요청 수행
            call.enqueue(object : Callback<LoginDto> {
                override fun onResponse(call: Call<LoginDto>, response: Response<LoginDto>) {
                    if (response.isSuccessful) {
                        Log.d("로그인 서버", response.body()?.data.toString())
                        LocalDataSource.setAccessToken("94006C12AAAE63C9951AC6AEB97290E5")
//                        response.body()?.data?.let { it1 -> LocalDataSource.setAccessToken(it1.sessionId) }
//                        response.body()?.data?.let { it1 -> LocalDataSource.setUserId(it1.userId) }
                        _isKakaoLogin.value = true
                    } else {
                        Log.d("로그인 서버", "서버통신 오류")
                    }
                }

                override fun onFailure(call: Call<LoginDto>, t: Throwable) {
                    // 통신 실패 처리
                    Log.d("로그인 서버", t.message.toString())
                }
            })
        }.handleResult(token, error)
    }

    fun kakaoLogin() = viewModelScope.launch {
        kakaoLoginService.startKakaoLogin(kakaoLoginCallback)
    }
}