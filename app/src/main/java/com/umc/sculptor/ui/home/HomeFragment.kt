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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

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

        // 코루틴을 사용하기 위한 스코프 생성
        val coroutineScope = CoroutineScope(Dispatchers.Main)

        // 데이터 받아오고 처리하는 함수
        fun fetchDataAndProcess() {
            coroutineScope.launch {
                try {
                    // 서버에서 데이터 받아오기
                    val response = homeService.getHome().execute()

                    if (response.isSuccessful) {
                        val data: FollowingsStones? = response.body()
                        data?.let {
                            // xml에 있는 값 매핑
                            binding.tvFriendsStatue.text = it.data.userName

                            // 리사이클러뷰 리스트 매핑
                            itemList = it.data.follwings
                            Log.d("server", "success")

                            // 어댑터 설정
                            friendStatueAdapter = FriendStatueAdapter(itemList)
                            binding.rvFriendStatue.adapter = friendStatueAdapter
                            binding.rvFriendStatue.layoutManager = LinearLayoutManager(context)
                        }
                    } else {
                        // 실패 처리
                        Log.d("server", "서버 에러 발생")
                        Toast.makeText(requireContext(), "서버 에러 발생", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    // 예외 처리
                    e.printStackTrace()
                    Log.d("server", "서버 통신 오류 발생")
                }
            }
        }


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