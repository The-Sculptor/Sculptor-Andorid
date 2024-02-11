package com.umc.sculptor.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.data.model.dto.LoginViewModel

class LoginViewModelFactory(private val kakaoLoginService: KakaoLoginService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(kakaoLoginService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}