package com.umc.sculptor.ui.workshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentDetailWorkshopBinding


class DetailWorkshopFragment : BaseFragment<FragmentDetailWorkshopBinding>(R.layout.fragment_detail_workshop) {
    private var itemDatas = ArrayList<Date>()

    private lateinit var boxAdapter: BoxAdapter
    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()

//        val dummy:ArrayList<Date> = ArrayList()
//        dummy.add(Date(""))
//
//    dateAdapter = DateAdapter(dummy)
//
//        binding.recyclerview2.adapter = boxAdapter
//        binding.recyclerview2.layoutManager = LinearLayoutManager(context)
//    }

    }

    override fun initAfterBinding() {
        super.initAfterBinding()
        binding.record.setOnClickListener {
            navController.navigate(R.id.action_DetailworkshopFragment_to_TodaycheckFragment)}
        binding.back.setOnClickListener {
            navController.navigate(R.id.action_DetailworkshopFragment_to_workshopFragment)} }}
//        val itemRVAdapter = DateAdapter(itemDatas)
//        binding.recyclerview2.adapter = itemRVAdapter
//



