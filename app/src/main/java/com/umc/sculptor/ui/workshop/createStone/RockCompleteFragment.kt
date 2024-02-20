package com.umc.sculptor.ui.workshop.createStone

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Category
import com.umc.sculptor.data.model.dto.CreateStoneRequestDto
import com.umc.sculptor.data.model.dto.CreateStoneResponseDto
import com.umc.sculptor.data.model.dto.Data
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.data.model.remote.home.DataXXX
import com.umc.sculptor.data.model.remote.home.FollowResponseDto
import com.umc.sculptor.databinding.FragmentCreateRockCategoryBinding
import com.umc.sculptor.databinding.FragmentCreateRockCompleteBinding
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.databinding.FragmentWorkshopBinding
import com.umc.sculptor.login.LocalDataSource
import com.umc.sculptor.ui.workshop.DeleteDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RockCompleteFragment : BaseFragment<FragmentCreateRockCompleteBinding>(R.layout.fragment_create_rock_complete) {
    val viewModel: NewStoneViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NewStoneViewModel::class.java)
    }

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()


        binding.tvStoneGoal.text = "목표  |  "+ (viewModel.getNewStone().value?.goal ?: "")
        binding.tvStoneDate.text = "시작일  |  "+ (viewModel.getNewStone().value?.start_date?.substring(0, 10) ?: "")
        binding.tvStoneName.text = viewModel.getNewStone().value?.name ?: ""
        if(viewModel.getNewStone().value?.dday?.get(0) == '-')
            binding.tvDDay.text = "D"+viewModel.getNewStone().value?.dday
        else
            binding.tvDDay.text = "D+"+viewModel.getNewStone().value?.dday

        when(viewModel.getNewStone().value?.category){
            Category.WORKOUT ->{
                binding.tvCategory.text = "운동"
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.material_symbols_exercise_outline)
                binding.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
            }
            Category.STUDY -> {
                binding.tvCategory.text = "공부"
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.mdi_book_open_blank_variant_outline)
                binding.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
            }
            Category.DAILY -> {
                binding.tvCategory.text = "일상"
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.lucide_lamp)
                binding.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
            }
            else -> binding.tvCategory.text = ""
        }

    }


    override fun initAfterBinding() {
        super.initAfterBinding()


        binding.btnComplete.setOnClickListener {
            CreateConfirmDialog().show(requireActivity().supportFragmentManager,"dialog")
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}