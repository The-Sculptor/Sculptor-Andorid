package com.umc.sculptor.ui.workshop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.api.Data
import com.umc.sculptor.api.DataX
import com.umc.sculptor.api.DataXX
import com.umc.sculptor.api.getAllAchieves
import com.umc.sculptor.api.getOneStone
import com.umc.sculptor.api.getStones
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.remote.home.FollowingsStones
import com.umc.sculptor.databinding.FragmentDetailWorkshopBinding
import com.umc.sculptor.login.LocalDataSource
import okhttp3.Cookie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID


class DetailWorkshopFragment : BaseFragment<FragmentDetailWorkshopBinding>(R.layout.fragment_detail_workshop) {
    private var itemDatas = ArrayList<Date>()

    private lateinit var dateAdapter: DateAdapter



    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        var itemList: List<DataX> = ArrayList<DataX>()

        // 서버 통신 요청
        val call: Call<getOneStone> = ServicePool.workshopService.getOneStone(

            stoneId = UUID.randomUUID() ,
            accessToken = "JSESSIONID="+LocalDataSource.getAccessToken().toString(),
            contentType = "String"
        )

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<getOneStone> {
            override fun onResponse(call: Call<getOneStone> , response: Response<getOneStone>) {
                if (response.isSuccessful) {
                    itemList = (response.body()?.data ?: ArrayList<DataX>()) as List<DataX>
                    dateAdapter.datelist = itemList as ArrayList<Date>
                    dateAdapter.notifyDataSetChanged()
                    Log.d("돌 정보 조회 성공",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("실패 사유입니다.","서버 내부 에러")
                }
            }

            override fun onFailure(call: Call<getOneStone> , t: Throwable) {
                // 통신 실패 처리
                Log.d("홈 서버",t.message.toString())
            }
        })


        itemDatas.add(Date("17","달성","+10g",R.drawable.icon_circle))
        itemDatas.add(Date("16","절반 달성","+5g",R.drawable.icon_triangle))
        itemDatas.add(Date("15","미달성","+0g", R.drawable.icon_x))
        itemDatas.add(Date("14","달성","+10g",R.drawable.icon_circle))
        itemDatas.add(Date("13","절반 달성","+5g",R.drawable.icon_triangle))
        itemDatas.add(Date("12","미달성","+0g",R.drawable.icon_x))




        dateAdapter= DateAdapter(itemDatas)

        binding.rvFriendStatue.adapter = dateAdapter
        binding.rvFriendStatue.layoutManager = LinearLayoutManager(context)

        // 아이템 클릭 리스너 설정
        dateAdapter.setOnItemClickListener(object : DateAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                navController.navigate(R.id.action_workshopFragment_to_detailWorkshopFragment)
            }
        })


    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.record.setOnClickListener {
            navController.navigate(R.id.action_detailWorkshopFragment_to_todaycheckFragment)
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideIconAndShowBack(false)
    }
}


