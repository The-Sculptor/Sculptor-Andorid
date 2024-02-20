package com.umc.sculptor.ui.museum

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.FriendMuseumViewModel
import com.umc.sculptor.data.model.dto.MuseumDetailViewModel
import com.umc.sculptor.data.model.remote.home.DataX
import com.umc.sculptor.data.model.remote.home.DataXXX
import com.umc.sculptor.data.model.remote.home.FollowResponseDto
import com.umc.sculptor.data.model.remote.home.ResearchResponseDto
import com.umc.sculptor.data.model.remote.museum.Museum
import com.umc.sculptor.data.model.remote.museum.Stone
import com.umc.sculptor.databinding.FragmentMuseumProfileOtherBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuseumProfileOtherFragment : BaseFragment<FragmentMuseumProfileOtherBinding>(R.layout.fragment_museum_profile_other) {
    val viewModel: FriendMuseumViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FriendMuseumViewModel::class.java)
    }

    val viewModel2: MuseumDetailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MuseumDetailViewModel::class.java)
    }

    lateinit var id:String

    private lateinit var museumSculptorRVAdapter: MuseumSculptorAdapter
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

        viewModel.follow.observe(viewLifecycleOwner) { follow ->
            Log.d("Received message", follow.toString())
            if(follow == true){
                binding.museumProfileFollow.setBackgroundResource(R.drawable.btn_following)
            }
            if(follow == false){
                binding.museumProfileFollow.setBackgroundResource(R.drawable.btn_follow)
            }

        }

        if(viewModel.follow.value == true){
            binding.museumProfileFollow.setBackgroundResource(R.drawable.btn_following)
        }
        if(viewModel.follow.value == false){
            binding.museumProfileFollow.setBackgroundResource(R.drawable.btn_follow)
        }

        var itemList : List<Stone> =ArrayList<Stone>()
        // 서버 통신 요청
        val call: Call<Museum> = ServicePool.museumService.getMuseum("JSESSIONID="+ LocalDataSource.getAccessToken().toString(), viewModel.message.value!!)

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<Museum> {
            override fun onResponse(call: Call<Museum>, response: Response<Museum>) {
                if (response.isSuccessful) {
                    var itemList = response.body()?.data?.stones
                    if (itemList != null) {
                        museumSculptorRVAdapter.sculptorList=itemList
                    }
                    museumSculptorRVAdapter.notifyDataSetChanged()
                    val data=response.body()?.data
                    if (data!=null){
                        binding.museumSculptorNum.text=data.stoneCount.toString()
                        binding.museumFollowNum.text=data.followerCount.toString()
                        binding.museumFollowingNum.text=data.followingCount.toString()
                        binding.museumIntroName.text=data.nickname
                        binding.museumIntroText.text=data.introduction
                        context?.let {
                            Glide.with(it)
                                .load(data.profileImage)
                                .placeholder(R.drawable.ellipse) // 이미지 로딩 중에 표시될 placeholder 이미지
                                .error(R.drawable.ellipse) // 이미지 로딩 실패 시 표시될 이미지
                                .into(binding.cardView)
                        }
                    }

                    Log.d("박물관 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("박물관 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<Museum>, t: Throwable) {
                // 통신 실패 처리
                Log.d("박물관 서버",t.message.toString())
            }
        })

        museumSculptorRVAdapter = MuseumSculptorAdapter(itemList)
        binding.museumProfileSculptorRv.adapter = museumSculptorRVAdapter
        binding.museumProfileSculptorRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)



        museumSculptorRVAdapter.setMyItemClickListener(object: MuseumSculptorAdapter.MyItemClickListener{

            override fun onItemClick(id:String) {
                viewModel2.stoneid.value = id
                navController.navigate(R.id.action_museumProfileMeFragment_to_museumSculptorFragment)
            }

        })
    }

    override fun initDataBinding() {
        super.initDataBinding()

    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.museumProfileFollow.setOnClickListener {
            binding.museumProfileFollowing.visibility = View.VISIBLE
            binding.museumProfileFollow.visibility = View.GONE
        }
        binding.museumProfileFollowing.setOnClickListener {
            binding.museumProfileFollowing.visibility = View.GONE
            binding.museumProfileFollow.visibility = View.VISIBLE
        }


        binding.museumProfileFollow.setOnClickListener {
            val call: Call<FollowResponseDto> = ServicePool.homeService.follow(
                "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
                id)

            // 비동기적으로 요청 수행
            call.enqueue(object : Callback<FollowResponseDto> {
                override fun onResponse(call: Call<FollowResponseDto>, response: Response<FollowResponseDto>) {
                    if (response.isSuccessful) {
                        val itemList = response.body()?.data
                        viewModel.follow.value = itemList?.isFollowing
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