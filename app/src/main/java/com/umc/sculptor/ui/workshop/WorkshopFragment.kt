package com.umc.sculptor.ui.workshop

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentTodaycheckBinding
import com.umc.sculptor.databinding.FragmentWorkshopBinding

class WorkshopFragment: BaseFragment<FragmentWorkshopBinding>(R.layout.fragment_workshop) {

    private var itemDatas = ArrayList<Box>()

    private lateinit var boxAdapter: BoxAdapter
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        boxAdapter = BoxAdapter(ArrayList())

        binding.recyclerview.adapter = boxAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        // If you want to add some initial data, you can do it like this
        itemDatas.add(Box("Sample Data 1"))
        itemDatas.add(Box("Sample Data 2"))

        // Update the existing adapter with the initial data
        boxAdapter.updateData(itemDatas)

        Log.d("WorkshopFragment", "initAfterBinding executed")


    }
}