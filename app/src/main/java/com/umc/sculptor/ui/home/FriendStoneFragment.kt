package com.umc.sculptor.ui.home

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.FriendMuseumViewModel
import com.umc.sculptor.data.model.dto.FriendStoneViewModel
import com.umc.sculptor.data.model.remote.FollowingStoneDetail
import com.umc.sculptor.data.model.remote.home.FollowResponseDto
import com.umc.sculptor.databinding.FragmentHomeFriendStoneBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendStoneFragment : BaseFragment<FragmentHomeFriendStoneBinding>(R.layout.fragment_home_friend_stone) {
    val viewModel: FriendStoneViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FriendStoneViewModel::class.java)
    }

    val viewModel2: FriendMuseumViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FriendMuseumViewModel::class.java)
    }

    private var isFollow :Boolean = true

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)


    }

    override fun initDataBinding() {
        super.initDataBinding()

        // 서버 통신 요청
        val call: Call<FollowingStoneDetail> = ServicePool.homeService.getFriendStone("JSESSIONID="+ LocalDataSource.getAccessToken().toString(),viewModel.friendId.value.toString())

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<FollowingStoneDetail> {
            override fun onResponse(call: Call<FollowingStoneDetail>, response: Response<FollowingStoneDetail>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    if (data != null) {
                        isFollow = data.isFollowing
                        if (!data.isFollowing){
                            binding.blurImg.visibility = View.VISIBLE
                            binding.btnFollowing.setBackgroundResource(R.drawable.btn_follow)
                        }
                        binding.tvNickname.text = data.nickname
                        binding.tvDDay.text = data.stone.dDay
                        binding.tvAchievementRate.text = data.stone.achievementRate.toString()+"%"
                        binding.tvStatueName.text = data.stone.name
                        binding.tvGoal.text=data.stone.goal
                        binding.tvGoalDate.text = data.stone.startDate

                    }
                    Log.d("친구 돌 서버",response.body()?.data.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("친구 돌 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<FollowingStoneDetail>, t: Throwable) {
                // 통신 실패 처리
                Log.d("친구 돌 서버",t.message.toString())
            }
        })


    }


    override fun initAfterBinding() {
        super.initAfterBinding()


        binding.btnFollowing.setOnClickListener {
            follow()
        }

        binding.btnGoMuseum.setOnClickListener {
            viewModel2.follow.value = isFollow
            viewModel2.message.value = viewModel.friendId.value.toString()
            navController.navigate(R.id.action_friendStoneFragment_to_museumProfileOtherFragment)
        }
    }

    private fun compareDrawables(drawable1: Drawable?, drawable2: Drawable?): Boolean {
        if (drawable1 == null || drawable2 == null) {
            return false
        }

        if (drawable1.constantState == null || drawable2.constantState == null) {
            return false
        }

        return drawable1.constantState == drawable2.constantState
    }

    private fun follow() {

        // 비동기적으로 요청 수행
        viewModel.friendId.value?.let {
            ServicePool.homeService.follow(
                "JSESSIONID=" + LocalDataSource.getAccessToken().toString(),
                it
            )
        }?.enqueue(object : Callback<FollowResponseDto> {
            override fun onResponse(
                call: Call<FollowResponseDto>,
                response: Response<FollowResponseDto>
            ) {
                if (response.isSuccessful) {
                    if(response.body()?.data?.isFollowing == true){
                        binding.btnFollowing.setBackgroundResource(R.drawable.btn_following)
                        binding.blurImg.visibility = View.GONE
                    }
                    if(response.body()?.data?.isFollowing == false){
                        binding.btnFollowing.setBackgroundResource(R.drawable.btn_follow)
                        binding.blurImg.visibility = View.VISIBLE
                    }
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("다른 유저 박물관 서버", "서버통신 오류")
                }
            }

            override fun onFailure(call: Call<FollowResponseDto>, t: Throwable) {
                // 통신 실패 처리
                Log.d("다른 유저 박물관 서버", t.message.toString())
            }
        })
    }
}