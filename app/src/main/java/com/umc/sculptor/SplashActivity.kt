package com.umc.sculptor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.umc.sculptor.login.LocalDataSource

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_splash)


        var keyHash = Utility.getKeyHash(this)
        Log.d("키해시", " $keyHash")

        KakaoSdk.init(this, BuildConfig.NATIVE_APP_KEY)

        LocalDataSource.init(this)

        // 스플래시 화면 표시 후 메인 액티비티로 이동
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000) // 2초 후에 메인 액티비티로 이동
    }
}