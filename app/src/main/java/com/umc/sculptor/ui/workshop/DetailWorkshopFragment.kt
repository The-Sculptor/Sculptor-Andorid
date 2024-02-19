package com.umc.sculptor.ui.workshop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.WorkshopDetailViewModel
import com.umc.sculptor.data.model.remote.Achieve
import com.umc.sculptor.data.model.remote.AchievementCounts
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

    private var itemDatas = ArrayList<java.util.Date>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var dateAdapter: DateAdapter

//    override fun onCreateView(
//        inflater: LayoutInflater , container: ViewGroup? ,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_detail_workshop, container, false)
//        recyclerView = view.findViewById(R.id.rv_friend_statue)
//        return view
//    }


    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        val itemList: List<Achieve> = ArrayList<Achieve>()

        dateAdapter= DateAdapter(itemList)
        binding.rvFriendStatue.adapter = dateAdapter

        recyclerView = binding.rvFriendStatue
        recyclerView.layoutManager = LinearLayoutManager(context)

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
                    binding.tvAchievementRate.inputType = data?.achRate?:0
                    binding.iconCategory.text = data?.category
                    binding.tvDDay.text = data?.dday
                    binding.tvStoneDustGram.inputType= data?.powder?:0
                    binding.tvStartDate.text = data?.startDate
                    binding.tvGoal.text = data?.stoneGoal
                    binding.ivStone.setImageResource(R.drawable.example_stone)

                    Log.d(" 공방 돌 하나 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("공방 돌 하나 서버","서버 내부 에러")
                }
            }

            override fun onFailure(call: Call<getOneStone> , t: Throwable) {
                // 통신 실패 처리
                Log.d("공방 돌 하나 서버",t.message.toString())
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

                    // Calculate num_all, num_mid, num_none based on AchievementCounts
                    var numAll = 0
                    var numMid = 0
                    var numNone = 0
                    itemList?.forEach { achieve ->
                        when (achieve.achieveStatus) {
                            // Assuming `achieveType` is the property that determines the type of achievement
                            "A" -> numAll++
                            "B" -> numMid++
                            "C" -> numNone++
                        }
                    }

                    // Set the text accordingly
                    binding.numAll.text = response.body()?.data?.achievementCounts?.a.toString()
                    binding.numMid.text = numMid.toString()
                    binding.numNone.text = numNone.toString()
                } else {
                    // Handle unsuccessful response

                }
                     if (itemList != null) {
                        dateAdapter.datelist = itemList

                    dateAdapter.notifyDataSetChanged()
                    Log.d("공방 달성현황 서버",itemList.toString())

                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("공방 달성현황 서버" , "서버통신 오류")
                }
            }

            override fun onFailure(call: Call<getAllAchieves>, t: Throwable) {
                // 통신 실패 처리
                Log.d("공방 달성현황 서버",t.message.toString())
            }
        })



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


        binding.btnDelete.setOnClickListener {
            DeleteDialog().show(requireActivity().supportFragmentManager,"dialog")
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideIconAndShowBack(false)
    }
}


