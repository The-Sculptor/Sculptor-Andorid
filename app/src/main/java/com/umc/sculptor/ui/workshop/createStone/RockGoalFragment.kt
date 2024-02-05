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
            Category.HEALTH -> binding.tvCategory.text = "건강"
            Category.STUDY -> binding.tvCategory.text = "공부"
            Category.ETC -> binding.tvCategory.text = "기타"
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