package com.umc.sculptor.ui.workshop

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capjjang.rightnow.api.WorkshopService
import com.google.android.material.tabs.TabLayout
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.api.Data
import com.umc.sculptor.api.getStones
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.apiManager.ServicePool.workshopService
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.databinding.FragmentWorkshopBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Response

class WorkshopFragment: BaseFragment<FragmentWorkshopBinding>(R.layout.fragment_workshop) {
    private  lateinit var tabLayout: TabLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var boxAdapter: BoxAdapter

    var currentIndex : Int = 0
    lateinit var categoryWorkshop: CategoryWorkshop



    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        var itemList : List<Data> =ArrayList<Data>()

        // 서버 통신 요청
        val call: retrofit2.Call<getStones> =
            workshopService.getStones(
                accessToken = "JSESSIONID="+LocalDataSource.getAccessToken().toString()
        )

        // 비동기적으로 요청 수행
        call.enqueue(object : retrofit2.Callback<getStones> {
            override fun onResponse(call: retrofit2.Call<getStones> , response: Response<getStones>) {
                if (response.isSuccessful) {
                    itemList = (response.body()?.data ?: ArrayList<Data>())
                    boxAdapter.datalist= itemList
                    boxAdapter.notifyDataSetChanged()
                    Log.d("공방 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("공방 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: retrofit2.Call<getStones> , t: Throwable) {
                // 통신 실패 처리
                Log.d("공방 서버",t.message.toString())
            }
        })


        boxAdapter =  BoxAdapter(itemList)
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