package com.umc.sculptor.ui.workshop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.R
import com.umc.sculptor.api.DataXXX
import com.umc.sculptor.api.sculptStone

import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.WorkshopDetailViewModel
import com.umc.sculptor.data.model.remote.AchievementCounts
import com.umc.sculptor.data.model.remote.DataXX
import com.umc.sculptor.data.model.remote.getAllAchieves
import com.umc.sculptor.databinding.FragmentStonepowderBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StonepowderFragment : BaseFragment<FragmentStonepowderBinding>(R.layout.fragment_stonepowder) {
    val viewModel: WorkshopDetailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(WorkshopDetailViewModel::class.java)
    }


     var clickedButtonId = 0

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


   override fun initStartView() {
        super.initStartView()

    }

    override fun initDataBinding() {
        super.initDataBinding()

        val achievementCounts = when(clickedButtonId){
            R.id.icon_all -> AchievementCountsStone.A
            R.id.icon_mid -> AchievementCountsStone.B
            R.id.icon_none -> AchievementCountsStone.C
            else -> AchievementCountsStone.A
        }

        binding.tvTitle.text = "축하합니다!\n"+ (achievementCounts.value)+"g의 돌가루를 모았어요!"
        binding.gram.text = "+" + (achievementCounts.value)

    }
    override fun initAfterBinding() {
        super.initAfterBinding()

        var itemList: List<DataXX> = ArrayList<DataXX>()


        val call: retrofit2.Call<getAllAchieves> =
            ServicePool.workshopService.getAllAchieves(
                accessToken = "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
                stoneId = viewModel.id.value.toString())


        // 비동기적으로 요청 수행
        call.enqueue(object : retrofit2.Callback<getAllAchieves> {
            override fun onResponse(call: retrofit2.Call<getAllAchieves> , response: Response<getAllAchieves>) {
                if (response.isSuccessful) {

                    Log.d("공방 달성현황조회 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("공방 달성현황조회 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<getAllAchieves> , t: Throwable) {
                // 통신 실패 처리
                Log.d("공방 달성현황조회 서버",t.message.toString())
            }
        })


    binding.x2.setOnClickListener{
            navController.navigate(R.id.action_stonepowderFragment_to_workshopFragment)
        }
        binding.okButton.setOnClickListener {
            navController.navigate(R.id.action_stonepowderFragment_to_workshopFragment)
        }

    }
}
