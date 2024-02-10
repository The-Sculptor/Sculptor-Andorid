package com.umc.sculptor.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool.homeService
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.FriendStatue
import com.umc.sculptor.data.model.remote.home.FollowingsStones
import com.umc.sculptor.data.model.remote.home.Follwing
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.login.LocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


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

        var itemList : List<Follwing> =ArrayList<Follwing>()
//        dumy.add(FriendStatue("SONG"))
//        dumy.add(FriendStatue("SONG"))
//        dumy.add(FriendStatue("SONG"))
//        dumy.add(FriendStatue("SONG"))

        // 서버 통신 요청
        val call: Call<FollowingsStones> = homeService.getFollowingsStones("JSESSIONID="+LocalDataSource.getAccessToken().toString())

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<FollowingsStones> {
            override fun onResponse(call: Call<FollowingsStones>, response: Response<FollowingsStones>) {
                if (response.isSuccessful) {
                    itemList = response.body()?.data?.follwings ?: ArrayList<Follwing>()
                    Log.d("홈 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("홈 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<FollowingsStones>, t: Throwable) {
                // 통신 실패 처리
                Log.d("홈 서버",t.message.toString())
            }
        })

        // 코루틴을 사용하기 위한 스코프 생성
        friendStatueAdapter = FriendStatueAdapter(itemList)
        binding.rvFriendStatue.adapter = friendStatueAdapter
        binding.rvFriendStatue.layoutManager = LinearLayoutManager(context)


        // 아이템 클릭 리스너 설정
        friendStatueAdapter.setOnItemClickListener(object : FriendStatueAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
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