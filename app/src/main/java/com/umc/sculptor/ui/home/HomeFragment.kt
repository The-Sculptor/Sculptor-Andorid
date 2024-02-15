package com.umc.sculptor.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool.homeService
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.FriendStoneViewModel
import com.umc.sculptor.data.model.remote.home.Data
import com.umc.sculptor.data.model.remote.home.FollowingsStone
import com.umc.sculptor.data.model.remote.home.MyRepresentStone
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    val viewModel: FriendStoneViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FriendStoneViewModel::class.java)
    }

    private lateinit var friendStatueAdapter: FriendStatueAdapter
    @SuppressLint("ResourceAsColor")
    override fun initStartView() {
        super.initStartView()

        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).binding.mainLogo.visibility = View.VISIBLE
        (activity as MainActivity).hideIconAndShowBack(false)

    }

    override fun initDataBinding() {
        super.initDataBinding()

        var itemList : List<Data> =ArrayList<Data>()

        // 서버 통신 요청
        val presentStoneCall: Call<MyRepresentStone> = homeService.getMyRepresentStone("JSESSIONID="+LocalDataSource.getAccessToken().toString())

        // 비동기적으로 요청 수행
        presentStoneCall.enqueue(object : Callback<MyRepresentStone> {
            override fun onResponse(call: Call<MyRepresentStone>, response: Response<MyRepresentStone>) {
                if (response.isSuccessful) {
                    val stone = response.body()?.data?.stone
                    if (stone != null){
                        binding.myStaueCard.tvDDay.text = stone.dDay
                        binding.myStaueCard.tvAchievementRate.text=stone.achievementRate.toString()+"%"
                        binding.myStaueCard.tvGoal.text = stone.goal
                        binding.myStaueCard.tvStatueName.text= stone.name
                        binding.myStaueCard.tvGoalDate.text = stone.startDate
                    }
                    Log.d("홈 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("홈 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<MyRepresentStone>, t: Throwable) {
                // 통신 실패 처리
                Log.d("홈 서버",t.message.toString())
            }
        })

        // 서버 통신 요청
        val followingsStonesCall: Call<FollowingsStone> = homeService.getFollowingsStones("JSESSIONID="+LocalDataSource.getAccessToken().toString())

        // 비동기적으로 요청 수행
        followingsStonesCall.enqueue(object : Callback<FollowingsStone> {
            override fun onResponse(call: Call<FollowingsStone>, response: Response<FollowingsStone>) {
                if (response.isSuccessful) {
                    itemList = response.body()?.data ?: ArrayList<Data>()
                    friendStatueAdapter.friendStatueList = itemList
                    friendStatueAdapter.notifyDataSetChanged()
                    Log.d("홈 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("홈 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<FollowingsStone>, t: Throwable) {
                // 통신 실패 처리
                Log.d("홈 서버",t.message.toString())
            }
        })

        friendStatueAdapter = FriendStatueAdapter(itemList)
        binding.rvFriendStatue.adapter = friendStatueAdapter
        binding.rvFriendStatue.layoutManager = LinearLayoutManager(context)


        // 아이템 클릭 리스너 설정
        friendStatueAdapter.setOnItemClickListener(object : FriendStatueAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                viewModel.friendId.value = itemList[position].id
                navController.navigate(R.id.action_homeFragment_to_friendStoneFragment)
            }

        })

    }


    override fun initAfterBinding() {
        super.initAfterBinding()


        binding.etProfileSearch.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_searchFragment)
        }


    }

    @SuppressLint("ResourceAsColor")
    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).binding.mainLogo.visibility = View.GONE
    }


}