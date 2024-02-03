package com.umc.sculptor.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.sculptor.databinding.FragmentItemboughtBinding

class Item_BoughtFragment: Fragment() {
    lateinit var binding: FragmentItemboughtBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemboughtBinding.inflate(inflater,container,false)
        return binding.root
    }
}