package com.umc.sculptor.ui.workshop.createStone

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.data.model.dto.Stone
import com.umc.sculptor.databinding.FragmentCreateRockNameBinding

class RockNameFragment : BaseFragment<FragmentCreateRockNameBinding>(R.layout.fragment_create_rock_name) {
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


    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.etStoneName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변화가 시작될 때
            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트에 변화가 있을 때
                if (!binding.etStoneName.text.isNullOrEmpty()) {
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
            val newStone = Stone(binding.etStoneName.text.toString())
            viewModel.setNewStone(newStone)
            viewModel.getNewStone().value?.let { it1 -> Log.d("newStone", it1.name) }
            
            navController.navigate(R.id.action_rockNameFragment_to_rockCategoryFragment)
        }
    }



}