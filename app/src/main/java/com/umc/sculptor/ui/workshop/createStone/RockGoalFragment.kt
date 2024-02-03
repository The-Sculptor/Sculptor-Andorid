package com.umc.sculptor.ui.workshop.createStone

import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentCreateRockCategoryBinding
import com.umc.sculptor.databinding.FragmentCreateRockGoalBinding
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.databinding.FragmentWorkshopBinding

class RockGoalFragment : BaseFragment<FragmentCreateRockGoalBinding>(R.layout.fragment_create_rock_goal) {

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnComplete.setOnClickListener {
            navController.navigate(R.id.action_rockGoalFragment_to_rockDateFragment)
        }
    }
}