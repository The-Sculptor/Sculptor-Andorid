package com.umc.sculptor.ui.workshop

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.api.DataXXX
import com.umc.sculptor.api.sculptStone
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseBottomDialogFragment
import com.umc.sculptor.base.BaseDialog2Fragment
import com.umc.sculptor.base.BaseDialogFragment
import com.umc.sculptor.data.model.dto.WorkshopDetailViewModel
import com.umc.sculptor.data.model.remote.Achieve
import com.umc.sculptor.data.model.remote.SculptorRequest
import com.umc.sculptor.data.model.remote.home.MyPageResonseDto
import com.umc.sculptor.data.model.remote.login.LogoutDto
import com.umc.sculptor.databinding.DialogDeleteBinding
import com.umc.sculptor.databinding.DialogDeleteConfirmBinding
import com.umc.sculptor.databinding.DialogSculptorBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class SculptorDialog : BaseDialog2Fragment<DialogSculptorBinding>(R.layout.dialog_sculptor) {
    val viewModel: WorkshopDetailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(WorkshopDetailViewModel::class.java)
    }

    interface OnDialogDismissListener {
        fun onDismiss()
    }

    private var dismissListener: OnDialogDismissListener? = null

    fun setOnDialogDismissListener(listener: OnDialogDismissListener) {
        dismissListener = listener
    }

    override fun initStartView(){
        super.initStartView()


    }
    override fun initDataBinding() {
        super.initDataBinding()

    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnClose.setOnClickListener {
            dismiss()
        }
        binding.iconAll.setOnClickListener {
            sculptor("A")
        }

        binding.iconMid.setOnClickListener {
            sculptor("B")
        }

        binding.iconNone.setOnClickListener {
            sculptor("C")
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sculptor(achive: String){
        val call: retrofit2.Call<sculptStone> =
            ServicePool.workshopService.sculptStone(
                accessToken = "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
                stoneId = viewModel.id.value!!,
                body = SculptorRequest(achive, LocalDateTime.now().toString())
                )


        // 비동기적으로 요청 수행
        call.enqueue(object : retrofit2.Callback<sculptStone> {
            override fun onResponse(call: retrofit2.Call<sculptStone>, response: Response<sculptStone>) {
                if (response.isSuccessful) {
                    val itemList = (response.body()?.data ?: ArrayList<DataXXX>())
                    Log.d("공방 조각하기 서버",itemList.toString())
                    dismiss()
                    dismissListener?.onDismiss()


                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("공방 조각하기 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<sculptStone>, t: Throwable) {
                // 통신 실패 처리
                Log.d("공방 조각하기 서버",t.message.toString())
                Toast.makeText(context,"현재는 조각을 진행 할 수 없습니다.", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        })
    }

}