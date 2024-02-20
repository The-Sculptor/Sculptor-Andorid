package com.umc.sculptor.ui.workshop

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Category
import com.umc.sculptor.data.model.dto.WorkshopDetailViewModel
import com.umc.sculptor.data.model.remote.Achieve
import com.umc.sculptor.data.model.remote.getAllAchieves
import com.umc.sculptor.data.model.remote.getOneStone
import com.umc.sculptor.data.model.remote.login.LogoutDto
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
                    binding.tvStoneDustGram.text = data?.powder.toString()
                    binding.tvDDay.text = data?.dday
                    binding.tvAchievementRate.text = data?.achRate.toString()
                    binding.tvStartDate.text = data?.startDate?.take(10)
                    binding.tvGoal.text = data?.stoneGoal
                    binding.ivStone.setImageResource(R.drawable.example_stone)
                    data?.stoneStatus?.let { setIcon(it) }

                    when(data?.category){
                        "WORKOUT" ->{
                            binding.iconCategory.text = "운동"
                            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.material_symbols_exercise_outline)
                            binding.iconCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
                        }
                        "STUDY" -> {
                            binding.iconCategory.text = "공부"
                            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.mdi_book_open_blank_variant_outline)
                            binding.iconCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
                        }
                        "DAILY" -> {
                            binding.iconCategory.text = "일상"
                            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.lucide_lamp)
                            binding.iconCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
                        }
                        else -> binding.iconCategory.text = ""
                    }


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
        getRate()


        // 아이템 클릭 리스너 설정
        dateAdapter.setOnItemClickListener(object : DateAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                navController.navigate(R.id.action_workshopFragment_to_detailWorkshopFragment)
            }
        })


    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.ivDetailworkshopPlus.setOnClickListener {
            val dialogFragment = SculptorDialog()
            dialogFragment.setOnDialogDismissListener(object : SculptorDialog.OnDialogDismissListener {
                override fun onDismiss() {
                    // 다이얼로그가 닫힐 때 프래그먼트의 작업을 수행합니다.
                    // 여기에서는 onCreate 메서드를 호출하거나 필요한 작업을 수행할 수 있습니다.
                    requireActivity().supportFragmentManager.beginTransaction()
                        .detach(this@DetailWorkshopFragment)
                        .attach(this@DetailWorkshopFragment)
                        .commit()
                }
            })
            dialogFragment.show(requireActivity().supportFragmentManager,"dialog")

        }

        binding.record.setOnClickListener {
            val dialogFragment = SculptorDialog()
            dialogFragment.setOnDialogDismissListener(object : SculptorDialog.OnDialogDismissListener {
                override fun onDismiss() {
                    getRate()
                }
            })
            dialogFragment.show(requireActivity().supportFragmentManager,"dialog")
        }



        binding.btnDelete.setOnClickListener {
            DeleteDialog().show(requireActivity().supportFragmentManager,"dialog")
        }

        binding.ivIcon1.setOnClickListener {
            deleteMoss()
        }

        binding.ivIcon2.setOnClickListener {
            repairCrack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideIconAndShowBack(false)
    }

    private fun setIcon(stoneStatus: StoneStatus){
        when(stoneStatus){
            StoneStatus.MOSS -> {
                binding.ivIcon1.visibility = View.VISIBLE
                binding.ivStone.setImageResource(R.drawable.stone_moss)
            }
            StoneStatus.PLANT -> {
                binding.ivIcon1.visibility = View.VISIBLE
                binding.ivStone.setImageResource(R.drawable.stone_moss)
            }
            StoneStatus.S_CRACK -> {
                binding.ivIcon2.visibility = View.VISIBLE
            }
            StoneStatus.L_CRACK -> {
                binding.ivIcon2.visibility = View.VISIBLE
            }
            StoneStatus.BROKEN -> {
                binding.ivIcon2.visibility = View.VISIBLE
            }
            else -> {
                binding.ivIcon1.visibility = View.INVISIBLE
                binding.ivIcon2.visibility = View.INVISIBLE
            }


        }
    }

    private fun deleteMoss(){
        // 서버 통신 요청
        val call: Call<LogoutDto> = ServicePool.workshopService.removeMoss(
            "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
            viewModel.id.value.toString())

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<LogoutDto> {
            override fun onResponse(call: Call<LogoutDto>, response: Response<LogoutDto>) {
                if (response.isSuccessful) {
                    Log.d("이끼 제거 서버",response.toString())
                    if(response.body()?.code == 400){
                        Toast.makeText(context, "포인트가 부족합니다", Toast.LENGTH_LONG).show()
                    }

                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("이끼 제거 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<LogoutDto>, t: Throwable) {
                // 통신 실패 처리
                Log.d("이끼 제거 서버",t.message.toString())
            }
        })
    }

    private fun repairCrack(){
        // 서버 통신 요청
        val call: Call<LogoutDto> = ServicePool.workshopService.repairCrack(
            "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
            viewModel.id.value.toString())

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<LogoutDto> {
            override fun onResponse(call: Call<LogoutDto>, response: Response<LogoutDto>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    Log.d("균열 매우기 서버",response.message().toString())
                    if(response.body()?.code == 400){
                        Toast.makeText(context, "포인트가 부족합니다", Toast.LENGTH_LONG).show()
                    }


                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("균열 매우기 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<LogoutDto>, t: Throwable) {
                // 통신 실패 처리
                Log.d("균열 매우기 서버",t.message.toString())
            }
        })
    }

    fun getRate(){

        val call2: retrofit2.Call<getAllAchieves> = ServicePool.workshopService.getAllAchieves(
            accessToken = "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
            stoneId = viewModel.id.value.toString(),
        )

        call2.enqueue(object : Callback<getAllAchieves> {
            override fun onResponse(call: Call<getAllAchieves>, response: Response<getAllAchieves>) {
                if (response.isSuccessful) {
                    val itemList = response.body()?.data?.achieves
                    itemList?.let {
                        dateAdapter.datelist = it.reversed()
                        dateAdapter.notifyDataSetChanged()
                        Log.d("공방 달성현황 서버",itemList.toString())
                    }
                    binding.numAll.text = response.body()?.data?.achievementCounts?.a.toString()
                    binding.numMid.text = response.body()?.data?.achievementCounts?.b.toString()
                    binding.numNone.text = response.body()?.data?.achievementCounts?.c.toString()

                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("공방 달성현황 서버" , "서버통신 오류")
                }
            }

            override fun onFailure(call: Call<getAllAchieves>, t: Throwable) {
                // 통신 실패
                Log.d("공방 달성현황 서버",t.message.toString())
            }
        })
    }
    }


