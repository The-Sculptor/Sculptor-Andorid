package com.umc.sculptor.ui.store

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class StorePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        Log.d("adapter", "1")
        return when(position) {
            // 탭레이아웃1
            0 -> Item_MyStatueFragment() // MY
            1 -> Item_StoneFragment() // 원석
            2 -> Item_ReadyFragment() // 상품
            3 -> Item_ReadyFragment() // 테마
            else -> Item_MyStatueFragment()
        }
    }
}