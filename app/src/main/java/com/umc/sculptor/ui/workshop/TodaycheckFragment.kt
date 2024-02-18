package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.umc.sculptor.R
import com.umc.sculptor.api.Data
import com.umc.sculptor.api.DataX
import com.umc.sculptor.api.DataXXX
import com.umc.sculptor.api.getStones
import com.umc.sculptor.api.sculptStone
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Category
import com.umc.sculptor.databinding.FragmentTodaycheckBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Response


class TodaycheckFragment : BaseFragment<FragmentTodaycheckBinding>(R.layout.fragment_todaycheck) {
    private lateinit var dateAdapter: DateAdapter
    var currentIndex: Int = 0
    lateinit var iconTodaycheck: IconTodaycheck


    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()

        var itemList: List<DataXXX> = ArrayList<DataXXX>()

        val call: retrofit2.Call<sculptStone> =
            ServicePool.workshopService.sculptStone(
                accessToken = "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
                contentType ="")


        // 비동기적으로 요청 수행
        call.enqueue(object : retrofit2.Callback<sculptStone> {
            override fun onResponse(call: retrofit2.Call<sculptStone> , response: Response<sculptStone>) {
                if (response.isSuccessful) {
                    val itemList = (response.body()?.data ?: ArrayList<DataXXX>())
                    dateAdapter.datelist= itemList as List<DataX>
                    dateAdapter.notifyDataSetChanged()
                    Log.d("공방 조각하기 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("공방 조각하기 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<sculptStone> , t: Throwable) {
                // 통신 실패 처리
                Log.d("공방 조각하기 서버",t.message.toString())
            }
        })
    }


    var clickedButtonId = 0
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.x.setOnClickListener {
            navController.navigate(R.id.action_todaycheckFragment_to_workshopFragment)
        }
        binding.okButton.setOnClickListener {
            navController.navigate(R.id.action_todaycheckFragment_to_stonepowderFragment)
        }
        binding.iconAll.setOnClickListener {
            clickedButtonId = R.id.icon_all
            setCategoryBackground()
        }

        binding.iconMid.setOnClickListener {
            clickedButtonId = R.id.icon_mid
            setCategoryBackground()
        }

        binding.iconNone.setOnClickListener {
            clickedButtonId = R.id.icon_none
            setCategoryBackground()
        }


    }



    @SuppressLint("ResourceAsColor")
    private fun setCategoryBackground() {
        binding.iconAll.setBackgroundResource(if (clickedButtonId == R.id.icon_all) R.drawable.gray else R.drawable.round_4_rectangle_todaycheck)
        binding.iconMid.setBackgroundResource(if (clickedButtonId == R.id.icon_mid) R.drawable.gray else R.drawable.round_4_rectangle_todaycheck)
        binding.iconNone.setBackgroundResource(if (clickedButtonId == R.id.icon_none) R.drawable.gray else R.drawable.round_4_rectangle_todaycheck)

        val textColor = if (clickedButtonId != 0) R.color.black else R.color.gray_908F90
        binding.okButton.isEnabled = clickedButtonId != 0
        binding.okButton.setTextColor(ContextCompat.getColor(requireContext(), textColor))


    }
}









