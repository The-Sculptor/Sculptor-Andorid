package com.umc.sculptor.ui.museum

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.MuseumDetailViewModel
import com.umc.sculptor.data.model.dto.WorkshopDetailViewModel
import com.umc.sculptor.data.model.remote.museum.Data
import com.umc.sculptor.data.model.remote.museum.Museum
import com.umc.sculptor.data.model.remote.museum.Stone
import com.umc.sculptor.databinding.FragmentMuseumProfileMeBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuseumProfileMeFragment : BaseFragment<FragmentMuseumProfileMeBinding>(R.layout.fragment_museum_profile_me) {

    val viewModel: MuseumDetailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MuseumDetailViewModel::class.java)
    }

    private lateinit var museumSculptorRVAdapter: MuseumSculptorAdapter
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).hideIconAndShowBack(false)


    }

    override fun initDataBinding() {
        super.initDataBinding()

        var itemList : List<Stone> =ArrayList<Stone>()
        // 서버 통신 요청
        val call: Call<Museum> = ServicePool.museumService.getMuseum("JSESSIONID="+ LocalDataSource.getAccessToken().toString(), LocalDataSource.getUserId().toString())

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
                viewModel.stoneid.value = id
                navController.navigate(R.id.action_museumProfileMeFragment_to_museumSculptorFragment)
            }

        })


    }

    override fun initAfterBinding() {
        super.initAfterBinding()
        binding.museumBtnWrite.setOnClickListener{
            navController.navigate(R.id.action_museumProfileMeFragment_to_museumEditFragment)
        }

    }

}