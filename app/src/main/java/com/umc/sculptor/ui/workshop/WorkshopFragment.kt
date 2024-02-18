package com.umc.sculptor.ui.workshop

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.api.Data
import com.umc.sculptor.api.getStones
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentWorkshopBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Response

class WorkshopFragment: BaseFragment<FragmentWorkshopBinding>(R.layout.fragment_workshop) {

    private var itemDatas = ArrayList<Box>()
    private lateinit var boxAdapter: BoxAdapter


    var currentIndex : Int = 0
    lateinit var categoryWorkshop: CategoryWorkshop

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).hideIconAndShowBack(false)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        var itemList : List<Box> =ArrayList<Box>()

        // 서버 통신 요청
        val call: retrofit2.Call<getStones> = ServicePool.workshopService.getStones(
             category = "EXERCISE",
            "JSESSIONID="+LocalDataSource.getAccessToken().toString(),
            contentType ="application/json"
        )

        // 비동기적으로 요청 수행
        call.enqueue(object : retrofit2.Callback<getStones> {
            override fun onResponse(call: retrofit2.Call<getStones> , response: Response<getStones>) {
                if (response.isSuccessful) {
                    itemList = (response.body()?.data ?: ArrayList<Data>()) as List<Box>
                    boxAdapter.boxlist= itemList as ArrayList<Box>
                    boxAdapter.notifyDataSetChanged()

                    Log.d("홈 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("홈 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: retrofit2.Call<getStones> , t: Throwable) {
                // 통신 실패 처리
                Log.d("홈 서버",t.message.toString())
            }
        })


        itemDatas.add(Box("D- 66","UX리서치","2024.01.04"))
        itemDatas.add(Box("D- 66","조깅하기","2024.01.04"))
        itemDatas.add(Box("D- 66","UX리서치","2024.01.04"))


        boxAdapter =  BoxAdapter(itemDatas)
        binding.recyclerview.adapter = boxAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)


        // 아이템 클릭 리스너 설정
        boxAdapter.setOnItemClickListener(object : BoxAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                navController.navigate(R.id.action_workshopFragment_to_detailWorkshopFragment)
            }
        })

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: WorkshopFragment.OnItemClickListener? = null
    fun setOnItemClickListener(listener: WorkshopFragment.OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun initAfterBinding() {
        super.initAfterBinding()


        binding.btnPlus.setOnClickListener {
            navController.navigate(R.id.action_workshopFragment_to_rockNameFragment)
        }


    }


}