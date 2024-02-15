package com.umc.sculptor.ui.workshop.createStone

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Category
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.databinding.FragmentCreateRockCategoryBinding

class RockCategoryFragment : BaseFragment<FragmentCreateRockCategoryBinding>(R.layout.fragment_create_rock_category) {
    val viewModel: NewStoneViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NewStoneViewModel::class.java)
    }
    var currentIndex : Int = 0
    lateinit var category : Category

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        binding.tvStoneName.text = viewModel.getNewStone().value?.name ?: "null"

    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.tvCategoryEtc.setOnClickListener {
            if(currentIndex == 3)
                currentIndex = 0
            else
                currentIndex = 3
            setCategoryBackground()
        }

        binding.tvCategoryHealth.setOnClickListener {
            if(currentIndex == 1)
                currentIndex = 0
            else
                currentIndex = 1
            setCategoryBackground()
        }

        binding.tvCategoryStudy.setOnClickListener {
            if(currentIndex == 2)
                currentIndex = 0
            else
                currentIndex = 2
            setCategoryBackground()
        }

        binding.btnComplete.setOnClickListener {
            viewModel.setNewStoneCategory(category)
            navController.navigate(R.id.action_rockCategoryFragment_to_rockGoalFragment)
        }

    }

    private fun setCategoryBackground(){
        when(currentIndex){
            1 -> {
                category = Category.WORKOUT
                binding.tvCategoryStudy.setBackgroundResource(R.drawable.round_4_rectangle_purple)
                binding.tvCategoryHealth.setBackgroundResource(R.drawable.round_4_rectangle_purple_border)
                binding.tvCategoryEtc.setBackgroundResource(R.drawable.round_4_rectangle_purple)
            }
            2 -> {
                category = Category.STUDY
                binding.tvCategoryStudy.setBackgroundResource(R.drawable.round_4_rectangle_purple_border)
                binding.tvCategoryHealth.setBackgroundResource(R.drawable.round_4_rectangle_purple)
                binding.tvCategoryEtc.setBackgroundResource(R.drawable.round_4_rectangle_purple)
            }
            3 -> {
                category = Category.DAILY
                binding.tvCategoryStudy.setBackgroundResource(R.drawable.round_4_rectangle_purple)
                binding.tvCategoryHealth.setBackgroundResource(R.drawable.round_4_rectangle_purple)
                binding.tvCategoryEtc.setBackgroundResource(R.drawable.round_4_rectangle_purple_border)
            }
            else ->{
                binding.tvCategoryStudy.setBackgroundResource(R.drawable.round_4_rectangle_purple)
                binding.tvCategoryHealth.setBackgroundResource(R.drawable.round_4_rectangle_purple)
                binding.tvCategoryEtc.setBackgroundResource(R.drawable.round_4_rectangle_purple)
            }
        }

        if (currentIndex > 0) {
            binding.btnComplete.isEnabled = true
            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        } else {
            binding.btnComplete.isEnabled = false
            binding.btnComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_908F90))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}