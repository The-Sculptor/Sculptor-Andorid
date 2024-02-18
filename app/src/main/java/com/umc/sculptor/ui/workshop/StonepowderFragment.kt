package com.umc.sculptor.ui.workshop

import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentStonepowderBinding


class StonepowderFragment : BaseFragment<FragmentStonepowderBinding>(R.layout.fragment_stonepowder) {

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()



//        val data = viewModel.getAchieves.value
//        if(data != null) {
//            val createStoneRequestDto = CreateStoneRequestDto(
//                data.category.toString(),
//                data.start_date,
//                data.goal,
//                data.name
//            )
//            Log.d("돌생성 서버", createStoneRequestDto.toString())
//            val call: Call<CreateStoneResponseDto> = ServicePool.workshopService.createStone(
//                "JSESSIONID=" + LocalDataSource.getAccessToken().toString(),
//                createStoneRequestDto
//            )
//
//            // 비동기적으로 요청 수행
//            call.enqueue(object : Callback<CreateStoneResponseDto> {
//                override fun onResponse(
//                    call: Call<CreateStoneResponseDto> ,
//                    response: Response<CreateStoneResponseDto>
//                ) {
//                    if (response.isSuccessful) {
//                        val itemList = response.body()?.data ?: ArrayList<Data>()
//                        Log.d("다른 유저 박물관 서버" , itemList.toString())
//                    } else {
//                        // 서버에서 오류 응답을 받은 경우 처리
//                        Log.d("다른 유저 박물관 서버" , "서버통신 오류")
//                    }
//                }
//
//                override fun onFailure(call: Call<CreateStoneResponseDto> , t: Throwable) {
//                    // 통신 실패 처리
//                    Log.d("다른 유저 박물관 서버" , t.message.toString())
//                }
//            })

    }
    override fun initAfterBinding() {
        super.initAfterBinding()
        binding.x2.setOnClickListener{
            navController.navigate(R.id.action_stonepowderFragment_to_workshopFragment)
        }
        binding.okButton.setOnClickListener {
            navController.navigate(R.id.action_stonepowderFragment_to_workshopFragment)
        }

    }
}
