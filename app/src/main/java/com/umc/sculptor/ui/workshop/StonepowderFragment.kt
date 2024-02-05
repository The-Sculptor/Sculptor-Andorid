package com.umc.sculptor.ui.workshop

import androidx.appcompat.app.AppCompatActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentStonepowderBinding


class StonepowderFragment : BaseFragment<FragmentStonepowderBinding>(R.layout.fragment_stonepowder) {

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()
        binding.x.setOnClickListener{
            navController.navigate(R.id.action_stonepowderFragment_to_workshopFragment)
        }
        binding.okButton.setOnClickListener {
            navController.navigate(R.id.action_todaycheckFragment_to_stonepowderFragment)
        }

    }
}