package com.umc.sculptor.ui.workshop.createStone

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.databinding.FragmentCreateRockCategoryBinding
import com.umc.sculptor.databinding.FragmentCreateRockDateBinding
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.databinding.FragmentWorkshopBinding

class RockDateFragment : BaseFragment<FragmentCreateRockDateBinding>(R.layout.fragment_create_rock_date) {
    val viewModel: NewStoneViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NewStoneViewModel::class.java)
    }

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        Log.d("stoneViewModel", viewModel.getNewStone().value.toString())
    }


    override fun initAfterBinding() {
        super.initAfterBinding()

//        binding.etStoneDate.setOnClickListener {
//
//        }

        binding.btnComplete.setOnClickListener {
            navController.navigate(R.id.action_rockDateFragment_to_rockCompleteFragment)
        }
    }
}