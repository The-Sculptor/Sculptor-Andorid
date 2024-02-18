package com.umc.sculptor.ui.workshop

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capjjang.rightnow.api.WorkshopService
import com.umc.sculptor.R
import com.umc.sculptor.api.AchievementCounts
import com.umc.sculptor.api.DataX
import com.umc.sculptor.api.DataXX
import com.umc.sculptor.api.getAllAchieves
import com.umc.sculptor.api.getOneStone
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.CreateStoneRequestDto
import com.umc.sculptor.data.model.dto.CreateStoneResponseDto
import com.umc.sculptor.data.model.dto.Data
import com.umc.sculptor.data.model.remote.home.MyPageResonseDto
import com.umc.sculptor.databinding.FragmentStonepowderBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID


class StonepowderFragment : BaseFragment<FragmentStonepowderBinding>(R.layout.fragment_stonepowder) {
    var clickedButtonId = 0
   override fun initStartView() {
        super.initStartView()

    }

    override fun initDataBinding() {
        super.initDataBinding()

        val achievementCounts = when(clickedButtonId){
            R.id.icon_all -> AchievementCounts(a = 10, b = 20, c = 30)
            R.id.icon_mid -> AchievementCounts(a = 5, b = 15, c = 25)
            R.id.icon_none -> AchievementCounts(a = 0, b = 10, c = 20)
            else -> AchievementCounts(a = 0, b = 0, c = 0) // 기본값 설정
        }

        binding.tvTitle.text = "축하합니다!\n"+ (achievementCounts.a)+"g의 돌가루를 모았어요!"
        binding.gram.text = "+" + (achievementCounts.a)

    }
    override fun initAfterBinding() {
        super.initAfterBinding()


        val call: retrofit2.Call<getAllAchieves> = ServicePool.workshopService.getAllAchieves(
            accessToken = "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
            contentType = ""
        )

//        call.enqueue(object : Callback<getAllAchieves> {
//            override fun onResponse(call: Call<getAllAchieves> , response: Response<getAllAchieves>) {
//                if (response.isSuccessful) {
//                    val data = response.body()?.data
//                    Log.d("공방 달성현황 서버",data.toString())
//
//                } else {
//                    // 서버에서 오류 응답을 받은 경우 처리
//                    Log.d("공방 달성현황 서버","서버통신 오류")
//                }
//            }
//
//            override fun onFailure(call: Call<MyPageResonseDto> , t: Throwable) {
//                // 통신 실패 처리
//                Log.d("공방 달성현황 서버",t.message.toString())
//            }
//        })



        binding.x2.setOnClickListener{
            navController.navigate(R.id.action_stonepowderFragment_to_workshopFragment)
        }
        binding.okButton.setOnClickListener {
            navController.navigate(R.id.action_stonepowderFragment_to_workshopFragment)
        }

    }
}
