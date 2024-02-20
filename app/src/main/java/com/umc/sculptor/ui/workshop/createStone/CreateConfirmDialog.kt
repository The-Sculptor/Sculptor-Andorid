package com.umc.sculptor.ui.workshop.createStone

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseDialogFragment
import com.umc.sculptor.data.model.dto.CreateStoneRequestDto
import com.umc.sculptor.data.model.dto.CreateStoneResponseDto
import com.umc.sculptor.data.model.dto.Data
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.data.model.dto.WorkshopDetailViewModel
import com.umc.sculptor.data.model.remote.login.LogoutDto
import com.umc.sculptor.databinding.DialogCreateConfirmBinding
import com.umc.sculptor.databinding.DialogDeleteConfirmBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateConfirmDialog : BaseDialogFragment<DialogCreateConfirmBinding>(R.layout.dialog_create_confirm) {
    val viewModel: NewStoneViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NewStoneViewModel::class.java)
    }

    override fun initStartView(){
        super.initStartView()


    }
    override fun initDataBinding() {
        super.initDataBinding()

    }
    override fun initAfterBinding() {
        super.initAfterBinding()


        binding.btnClose.setOnClickListener {
            dismiss()
        }

        binding.btnClose2.setOnClickListener {
            dismiss()
        }

        binding.btnConfirm.setOnClickListener {

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
                dismiss()
                navController.navigate(R.id.action_rockCompleteFragment_to_homeFragment)
            }
        }

    }

}