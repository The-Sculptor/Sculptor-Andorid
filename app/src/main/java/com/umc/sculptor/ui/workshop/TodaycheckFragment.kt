package com.umc.sculptor.ui.workshop

import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentStonepowderBinding
import com.umc.sculptor.databinding.FragmentTodaycheckBinding


class TodaycheckFragment : BaseFragment<FragmentTodaycheckBinding>(R.layout.fragment_todaycheck) {

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()
        binding.x.setOnClickListener{
            navController.navigate(R.id.action_todaycheckFragment_to_workshopFragment)
        }

    }
}