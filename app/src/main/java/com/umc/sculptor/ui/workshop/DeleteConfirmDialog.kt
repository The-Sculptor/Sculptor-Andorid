package com.umc.sculptor.ui.workshop

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.Log
import android.view.WindowManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseBottomDialogFragment
import com.umc.sculptor.base.BaseDialogFragment
import com.umc.sculptor.data.model.dto.WorkshopDetailViewModel
import com.umc.sculptor.data.model.remote.home.MyPageResonseDto
import com.umc.sculptor.data.model.remote.login.LogoutDto
import com.umc.sculptor.databinding.DialogDeleteBinding
import com.umc.sculptor.databinding.DialogDeleteConfirmBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteConfirmDialog : BaseDialogFragment<DialogDeleteConfirmBinding>(R.layout.dialog_delete_confirm) {
    val viewModel: WorkshopDetailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(WorkshopDetailViewModel::class.java)
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

            // 서버 통신 요청
            val call: Call<LogoutDto> = ServicePool.workshopService.deleteStone(
                "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
                viewModel.id.value.toString())

            // 비동기적으로 요청 수행
            call.enqueue(object : Callback<LogoutDto> {
                override fun onResponse(call: Call<LogoutDto>, response: Response<LogoutDto>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        Log.d("돌 삭제 서버",response.message().toString())
                        dismiss()
                        (activity as MainActivity).onBackPressed()

                    } else {
                        // 서버에서 오류 응답을 받은 경우 처리
                        Log.d("돌 삭제 서버","서버통신 오류")
                    }
                }

                override fun onFailure(call: Call<LogoutDto>, t: Throwable) {
                    // 통신 실패 처리
                    Log.d("돌 삭제 서버",t.message.toString())
                }
            })
        }

    }

}