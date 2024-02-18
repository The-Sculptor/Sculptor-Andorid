package com.umc.sculptor.ui.workshop

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.DataXXX
import com.umc.sculptor.data.model.remote.getStones
import com.umc.sculptor.apiManager.ServicePool.workshopService
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentWorkshopBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Response

class WorkshopFragment: BaseFragment<FragmentWorkshopBinding>(R.layout.fragment_workshop) {
    private  lateinit var tabLayout: TabLayout
    private lateinit var recyclerView: RecyclerView

    private var itemDatas = ArrayList<DataXXX>()
    private lateinit var boxAdapter: BoxAdapter

    var currentIndex : Int = 0



    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).hideIconAndShowBack(false)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        var itemList : List<DataXXX> =ArrayList<DataXXX>()

        getList("")

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

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // 선택된 탭에 대한 처리
                when(tab.position){
                    0-> getList("")
                    1-> getList(CategoryWorkshop.WORKOUT.toString())
                    2-> getList(CategoryWorkshop.STUDY.toString())
                    3-> getList(CategoryWorkshop.DAILY.toString())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // 선택이 해제된 탭에 대한 처리
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // 이미 선택된 탭을 다시 선택할 때의 처리
            }
        })


    }

    private fun getList(category: String){
        // 서버 통신 요청
        val call: retrofit2.Call<getStones> =
            workshopService.getStones(
                category = category,
                accessToken = "JSESSIONID="+LocalDataSource.getAccessToken().toString())


        // 비동기적으로 요청 수행
        call.enqueue(object : retrofit2.Callback<getStones> {
            override fun onResponse(call: retrofit2.Call<getStones>, response: Response<getStones>) {
                if (response.isSuccessful) {
                    val itemList = (response.body()?.data ?: ArrayList<DataXXX>())
                    boxAdapter.datalist= itemList
                    boxAdapter.notifyDataSetChanged()
                    Log.d("공방 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("공방 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: retrofit2.Call<getStones>, t: Throwable) {
                // 통신 실패 처리
                Log.d("공방 서버",t.message.toString())
            }
        })
    }

}