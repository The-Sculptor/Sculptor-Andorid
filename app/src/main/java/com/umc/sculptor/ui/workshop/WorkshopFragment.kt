package com.umc.sculptor.ui.workshop

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.FriendStatue
import com.umc.sculptor.databinding.FragmentTodaycheckBinding
import com.umc.sculptor.databinding.FragmentWorkshopBinding
import com.umc.sculptor.ui.home.FriendStatueAdapter

class WorkshopFragment: BaseFragment<FragmentWorkshopBinding>(R.layout.fragment_workshop) {

    private var itemDatas = ArrayList<Box>()

    private lateinit var boxAdapter: BoxAdapter


    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        itemDatas.add(Box("D- 66"))
        itemDatas.add(Box("D- 66"))

        boxAdapter = BoxAdapter(itemDatas)

        binding.recyclerview.adapter = boxAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)

        // 아이템 클릭 리스너 설정
        boxAdapter.setOnItemClickListener(object : BoxAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                navController.navigate(R.id.action_workshopFragment_to_detailWorkshopFragment)
            }
        })


    }


    override fun initAfterBinding() {
        super.initAfterBinding()



    }
}