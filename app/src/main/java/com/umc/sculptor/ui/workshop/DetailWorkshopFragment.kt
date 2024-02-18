package com.umc.sculptor.ui.workshop

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.WorkshopDetailViewModel
import com.umc.sculptor.data.model.remote.Achieve
import com.umc.sculptor.data.model.remote.DataX
import com.umc.sculptor.data.model.remote.getAllAchieves
import com.umc.sculptor.data.model.remote.getOneStone
import com.umc.sculptor.databinding.FragmentDetailWorkshopBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailWorkshopFragment : BaseFragment<FragmentDetailWorkshopBinding>(R.layout.fragment_detail_workshop) {
    val viewModel: WorkshopDetailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(WorkshopDetailViewModel::class.java)
    }

    private var itemDatas = ArrayList<Date>()

    private lateinit var dateAdapter: DateAdapter



    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        var itemList: List<Achieve> = ArrayList<Achieve>()

        // 서버 통신 요청
        val call: Call<getOneStone> = ServicePool.workshopService.getOneStone(
            accessToken = "JSESSIONID="+LocalDataSource.getAccessToken().toString(),
            stoneId = viewModel.id.value.toString()
        )

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<getOneStone> {
            override fun onResponse(call: Call<getOneStone> , response: Response<getOneStone>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    binding.tvName.text = data?.stoneName
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

        // 서버 통신 요청

        val call2: retrofit2.Call<getAllAchieves> = ServicePool.workshopService.getAllAchieves(
            accessToken = "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
            stoneId = viewModel.id.value.toString(),
        )

        call2.enqueue(object : Callback<getAllAchieves> {
            override fun onResponse(call: Call<getAllAchieves>, response: Response<getAllAchieves>) {
                if (response.isSuccessful) {
                    val itemList = response.body()?.data?.achieves
                    if (itemList != null) {
                        dateAdapter.datelist = itemList
                    }
                    dateAdapter.notifyDataSetChanged()
                    Log.d("공방 달성현황 서버",itemList.toString())

                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("공방 달성현황 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<getAllAchieves>, t: Throwable) {
                // 통신 실패 처리
                Log.d("공방 달성현황 서버",t.message.toString())
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


