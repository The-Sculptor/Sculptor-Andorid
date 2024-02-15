import java.util.Properties
import java.io.File

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
}

val properties = Properties().apply {
    load(File(rootProject.projectDir, "local.properties").inputStream())
}

android {
    namespace = "com.umc.sculptor"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.umc.sculptor"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "NATIVE_APP_KEY",properties.getProperty("native.app.key"))

        manifestPlaceholders["NATIVE_APP_KEY"] =
            properties.getProperty("native.app.key")
        buildConfigField("String", "AUTH_BASE_URL", properties.getProperty("base.url"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("de.hdodenhof:circleimageview:3.1.0")


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))

    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    implementation("com.kakao.sdk:v2-all:2.15.0") // 전체 모듈 설치, 2.11.0 버전부터 지원
    implementation ("com.kakao.sdk:v2-user:2.15.0") // 카카오 로그인
    implementation ("com.kakao.sdk:v2-talk:2.15.0") // 친구, 메시지(카카오톡)
    implementation ("com.kakao.sdk:v2-friend:2.15.0") // 카카오톡 소셜 피커, 리소스 번들 파일 포함

    implementation ("com.jakewharton.timber:timber:5.0.1")

    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    implementation("jp.wasabeef:glide-transformations:4.3.0")


}