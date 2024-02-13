package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Category
import com.umc.sculptor.databinding.FragmentTodaycheckBinding


class TodaycheckFragment : BaseFragment<FragmentTodaycheckBinding>(R.layout.fragment_todaycheck) {

    var currentIndex: Int = 0
    lateinit var iconTodaycheck: IconTodaycheck


    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()

    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.x.setOnClickListener {
            navController.navigate(R.id.action_todaycheckFragment_to_workshopFragment)
        }
        binding.okButton.setOnClickListener {
            navController.navigate(R.id.action_todaycheckFragment_to_stonepowderFragment)
        }


        binding.iconNone.setOnClickListener {
            if (currentIndex == 3)
                currentIndex = 0
            else
                currentIndex = 3
            setCategoryBackground()
        }

        binding.iconAll.setOnClickListener {
            if (currentIndex == 1)
                currentIndex = 0
            else
                currentIndex = 1
            setCategoryBackground()
        }

        binding.iconMid.setOnClickListener {
            if (currentIndex == 2)
                currentIndex = 0
            else
                currentIndex = 2
            setCategoryBackground()
        }

    }



    @SuppressLint("ResourceAsColor")
    private fun setCategoryBackground() {
        when (currentIndex) {
            1 -> {
                iconTodaycheck = IconTodaycheck.All
                binding.iconAll.setBackgroundResource(R.drawable.gray)
            }

            2 -> {
                iconTodaycheck = IconTodaycheck.Mid
                binding.iconMid.setBackgroundResource(R.drawable.gray)
            }

            3 -> {
                iconTodaycheck = IconTodaycheck.None
                binding.iconNone.setBackgroundResource(R.drawable.gray)
            }

            else -> {
                binding.iconAll.setBackgroundResource(R.drawable.round_4_rectangle_todaycheck)
                binding.iconMid.setBackgroundResource(R.drawable.round_4_rectangle_todaycheck)
                binding.iconNone.setBackgroundResource(R.drawable.round_4_rectangle_todaycheck)
            }
        }
        if (currentIndex > 0) {
            binding.okButton.isEnabled = true
            binding.okButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        } else {
            binding.okButton.isEnabled = false
            binding.okButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_908F90))
        }




    }
}









