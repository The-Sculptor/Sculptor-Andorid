package com.umc.sculptor.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.umc.sculptor.LoginActivity
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.apiManager.ServicePool.homeService
import com.umc.sculptor.apiManager.ServicePool.loginService
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.remote.home.Data
import com.umc.sculptor.data.model.remote.home.FollowingsStone
import com.umc.sculptor.data.model.remote.home.MyPageResonseDto
import com.umc.sculptor.data.model.remote.home.MyRepresentStone
import com.umc.sculptor.data.model.remote.home.PrivateDto
import com.umc.sculptor.data.model.remote.home.PrivateRequestDto
import com.umc.sculptor.data.model.remote.login.LoginDto
import com.umc.sculptor.data.model.remote.login.LogoutDto
import com.umc.sculptor.databinding.FragmentMypageBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)

        // 서버 통신 요청
        val call: Call<MyPageResonseDto> = ServicePool.homeService.getMypage("JSESSIONID="+ LocalDataSource.getAccessToken().toString())

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<MyPageResonseDto> {
            override fun onResponse(call: Call<MyPageResonseDto>, response: Response<MyPageResonseDto>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    Log.d("마이페이지 서버",data.toString())

                    if (data != null) {
                        binding.tvEmail.text = data.userName
                        context?.let {
                            Glide.with(it)
                                .load(data.profileImage)
                                .placeholder(R.drawable.ellipse) // 이미지 로딩 중에 표시될 placeholder 이미지
                                .error(R.drawable.ellipse) // 이미지 로딩 실패 시 표시될 이미지
                                .into(binding.ivProfileImg)
                        }
                        binding.switch1.isChecked = data.isPublic
                    }
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("마이페이지 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<MyPageResonseDto>, t: Throwable) {
                // 통신 실패 처리
                Log.d("마이페이지 서버",t.message.toString())
            }
        })

        binding.switch1.setOnClickListener {
            if(binding.switch1.isChecked){
                changePrivate(true)
            }else{
                changePrivate(false)
            }
        }

    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    @SuppressLint("ResourceAsColor")
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.logout.setOnClickListener {
            // 서버 통신 요청
            val call: Call<LogoutDto> = loginService.logout("JSESSIONID="+LocalDataSource.getAccessToken().toString())

            // 비동기적으로 요청 수행
            call.enqueue(object : Callback<LogoutDto> {
                override fun onResponse(call: Call<LogoutDto>, response: Response<LogoutDto>) {
                    if (response.isSuccessful) {
                        Log.d("로그아웃",response.message())
                        (activity as MainActivity).finish()
                        // 현재 액티비티를 종료하고 다시 시작
                        val intent = Intent(requireContext(), LoginActivity::class.java)
                        requireActivity().finish()
                        startActivity(intent)

                    } else {
                        // 서버에서 오류 응답을 받은 경우 처리
                        Log.d("로그아웃","서버통신 오류")
                    }
                }

                override fun onFailure(call: Call<LogoutDto>, t: Throwable) {
                    // 통신 실패 처리
                    Log.d("로그아웃",t.message.toString())
                }
            })
        }

    }

    private fun changePrivate(isPrivate : Boolean){
        val call: Call<PrivateDto> = ServicePool.homeService.changePrivate("JSESSIONID="+LocalDataSource.getAccessToken().toString(), PrivateRequestDto(isPrivate))

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<PrivateDto> {
            override fun onResponse(call: Call<PrivateDto>, response: Response<PrivateDto>) {
                if (response.isSuccessful) {
                    Log.d("홈 서버",response.body()?.data.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("홈 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<PrivateDto>, t: Throwable) {
                // 통신 실패 처리
                Log.d("홈 서버",t.message.toString())
            }
        })

    }
}