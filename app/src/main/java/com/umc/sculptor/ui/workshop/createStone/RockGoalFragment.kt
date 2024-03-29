package com.umc.sculptor.ui.workshop.createStone

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Category
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.databinding.FragmentCreateRockCategoryBinding
import com.umc.sculptor.databinding.FragmentCreateRockGoalBinding
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.databinding.FragmentWorkshopBinding

class RockGoalFragment : BaseFragment<FragmentCreateRockGoalBinding>(R.layout.fragment_create_rock_goal) {
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

        binding.tvStoneName.text = viewModel.getNewStone().value?.name ?: "null"
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


        binding.etStoneGoal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변화가 시작될 때
            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트에 변화가 있을 때
                if (!binding.etStoneGoal.text.isNullOrEmpty()) {
                    binding.btnComplete.isEnabled = true
                    binding.btnComplete.setTextColor(ContextCompat.getColor(context!!, R.color.black))
                } else {
                    binding.btnComplete.isEnabled = false
                    binding.btnComplete.setTextColor(ContextCompat.getColor(context!!, R.color.gray_908F90))
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변화가 끝났을 때
            }
        })


        binding.btnComplete.setOnClickListener {
            viewModel.setNewStoneGoal(binding.etStoneGoal.text.toString())
            navController.navigate(R.id.action_rockGoalFragment_to_rockDateFragment)
        }
    }

}