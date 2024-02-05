package com.umc.sculptor.ui.home

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginRight
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentMypageBinding

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)

    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    @SuppressLint("ResourceAsColor")
    override fun initAfterBinding() {
        super.initAfterBinding()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideIconAndShowBack(false)
    }
}