package com.umc.sculptor.ui.store

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class StorePagerAdapter2(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            // 탭레이아웃2
            0 -> Item_MyStatueFragment() // 나의 조각상
            1 -> Item_WearingFragment() // 착용중인 상품
            2 -> Item_BoughtFragment() // 구매한 상품
            else -> Item_MyStatueFragment()
        }
    }
}