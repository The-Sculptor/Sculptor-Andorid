package com.umc.sculptor.ui.workshop.createStone

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Category
import com.umc.sculptor.data.model.dto.CreateStoneRequestDto
import com.umc.sculptor.data.model.dto.CreateStoneResponseDto
import com.umc.sculptor.data.model.dto.Data
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.data.model.remote.home.DataXXX
import com.umc.sculptor.data.model.remote.home.FollowResponseDto
import com.umc.sculptor.databinding.FragmentCreateRockCategoryBinding
import com.umc.sculptor.databinding.FragmentCreateRockCompleteBinding
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.databinding.FragmentWorkshopBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RockCompleteFragment : BaseFragment<FragmentCreateRockCompleteBinding>(R.layout.fragment_create_rock_complete) {
    val viewModel: NewStoneViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NewStoneViewModel::class.java)
    }

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()


        binding.tvStoneGoal.text = "목표  |  "+ (viewModel.getNewStone().value?.goal ?: "")
        binding.tvStoneDate.text = "시작일  |  "+ (viewModel.getNewStone().value?.start_date?.substring(0, 10) ?: "")
        binding.tvStoneName.text = viewModel.getNewStone().value?.name ?: ""

        when(viewModel.getNewStone().value?.category){
            Category.WORKOUT -> binding.tvCategoryHealth.text = "건강"
            Category.STUDY -> binding.tvCategoryHealth.text = "공부"
            Category.DAILY -> binding.tvCategoryHealth.text = "기타"
            else -> binding.tvCategoryHealth.text = ""
        }
    }


    override fun initAfterBinding() {
        super.initAfterBinding()


        binding.btnComplete.setOnClickListener {

            val data = viewModel.getNewStone().value
            if(data != null) {
                val createStoneRequestDto = CreateStoneRequestDto(
                    data.category.toString(),
                    data.start_date,
                    data.goal,
                    data.name
                )
                Log.d("돌생성 서버", createStoneRequestDto.toString())
                val call: Call<CreateStoneResponseDto> = ServicePool.workshopService.createStone(
                    "JSESSIONID=" + LocalDataSource.getAccessToken().toString(),
                    createStoneRequestDto
                )

                // 비동기적으로 요청 수행
                call.enqueue(object : Callback<CreateStoneResponseDto> {
                    override fun onResponse(
                        call: Call<CreateStoneResponseDto>,
                        response: Response<CreateStoneResponseDto>
                    ) {
                        if (response.isSuccessful) {
                            val itemList = response.body()?.data ?: ArrayList<Data>()
                            Log.d("다른 유저 박물관 서버", itemList.toString())
                        } else {
                            // 서버에서 오류 응답을 받은 경우 처리
                            Log.d("다른 유저 박물관 서버", "서버통신 오류")
                        }
                    }

                    override fun onFailure(call: Call<CreateStoneResponseDto>, t: Throwable) {
                        // 통신 실패 처리
                        Log.d("다른 유저 박물관 서버", t.message.toString())
                    }
                })
                navController.navigate(R.id.action_rockCompleteFragment_to_homeFragment)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}