package com.umc.sculptor.ui.museum

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.FriendMuseumViewModel
import com.umc.sculptor.data.model.remote.home.DataX
import com.umc.sculptor.data.model.remote.home.DataXXX
import com.umc.sculptor.data.model.remote.home.FollowResponseDto
import com.umc.sculptor.data.model.remote.home.ResearchResponseDto
import com.umc.sculptor.databinding.FragmentMuseumProfileOtherBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuseumProfileOtherFragment : BaseFragment<FragmentMuseumProfileOtherBinding>(R.layout.fragment_museum_profile_other) {
    val viewModel: FriendMuseumViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FriendMuseumViewModel::class.java)
    }

    lateinit var id:String

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)

        // 데이터 수신
        viewModel.message.observe(viewLifecycleOwner) { message ->
            // 데이터 사용
            Log.d("Received message", message)
            id = message
        }
    }

    override fun initDataBinding() {
        super.initDataBinding()

    }


    override fun initAfterBinding() {
        super.initAfterBinding()


        binding.btnFollow.setOnClickListener {
            val call: Call<FollowResponseDto> = ServicePool.homeService.follow(
                "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
                id)

            // 비동기적으로 요청 수행
            call.enqueue(object : Callback<FollowResponseDto> {
                override fun onResponse(call: Call<FollowResponseDto>, response: Response<FollowResponseDto>) {
                    if (response.isSuccessful) {
                        val itemList = response.body()?.data?: ArrayList<DataXXX>()
                        Log.d("다른 유저 박물관 서버",itemList.toString())
                    } else {
                        // 서버에서 오류 응답을 받은 경우 처리
                        Log.d("다른 유저 박물관 서버","서버통신 오류")
                    }
                }

                override fun onFailure(call: Call<FollowResponseDto>, t: Throwable) {
                    // 통신 실패 처리
                    Log.d("다른 유저 박물관 서버",t.message.toString())
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}