package com.umc.sculptor.login

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import timber.log.Timber

class GlobalApplication : Application() {
    companion object {
        private lateinit var instance: GlobalApplication

        fun getContext(): GlobalApplication {
            return instance
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        // 다른 초기화 코드들
        Timber.plant(Timber.DebugTree())

        // Kakao SDK 초기화
        KakaoSdk.init(this, "{NATIVE_APP_KEY}")
    }
}
