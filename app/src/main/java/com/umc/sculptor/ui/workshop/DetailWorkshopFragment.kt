package com.umc.sculptor.ui.workshop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.api.Data
import com.umc.sculptor.api.DataX
import com.umc.sculptor.api.getOneStone
import com.umc.sculptor.api.getStones
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentDetailWorkshopBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Response


class DetailWorkshopFragment : BaseFragment<FragmentDetailWorkshopBinding>(R.layout.fragment_detail_workshop) {
    private var itemDatas = ArrayList<Date>()

    private lateinit var dateAdapter: DateAdapter



    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()


        itemDatas.add(Date("17","달성","+10g",R.drawable.icon_circle))
        itemDatas.add(Date("16","절반 달성","+5g",R.drawable.icon_triangle))
        itemDatas.add(Date("15","미달성","+0g", R.drawable.icon_x))
        itemDatas.add(Date("14","달성","+10g",R.drawable.icon_circle))
        itemDatas.add(Date("13","절반 달성","+5g",R.drawable.icon_triangle))
        itemDatas.add(Date("12","미달성","+0g",R.drawable.icon_x))


        dateAdapter= DateAdapter(itemDatas)

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


