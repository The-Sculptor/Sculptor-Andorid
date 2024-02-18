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
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.remote.DataX
import com.umc.sculptor.data.model.remote.getOneStone
import com.umc.sculptor.databinding.FragmentDetailWorkshopBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
            contentType ="" ,
            accessToken = "JSESSIONID="+LocalDataSource.getAccessToken().toString()
        )

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<getOneStone> {
            override fun onResponse(call: Call<getOneStone> , response: Response<getOneStone>) {
                if (response.isSuccessful) {
                    val itemList = (response.body()?.data ?: ArrayList<DataX>())
                    dateAdapter.datelist = itemList as ArrayList<DataX>
                    dateAdapter.notifyDataSetChanged()
                    Log.d(" 공방 돌 하나 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("공방 돌 하나 서버","서버 내부 에러")
                }
            }

            override fun onFailure(call: Call<getOneStone> , t: Throwable) {
                // 통신 실패 처리
                Log.d("홈 서버",t.message.toString())
            }
        })

        dateAdapter= DateAdapter(itemList)
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


