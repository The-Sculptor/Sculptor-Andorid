package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
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









