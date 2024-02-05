package com.umc.sculptor.ui.workshop.createStone

import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentCreateRockCategoryBinding
import com.umc.sculptor.databinding.FragmentCreateRockCompleteBinding
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.databinding.FragmentWorkshopBinding

class RockCompleteFragment : BaseFragment<FragmentCreateRockCompleteBinding>(R.layout.fragment_create_rock_complete) {

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()


        binding.btnComplete.setOnClickListener {
            navController.navigate(R.id.action_rockCompleteFragment_to_homeFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideIconAndShowBack(false)

    }
}