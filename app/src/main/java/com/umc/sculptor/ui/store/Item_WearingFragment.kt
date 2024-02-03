package com.umc.sculptor.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.databinding.FragmentItemboughtBinding
import com.umc.sculptor.databinding.FragmentItemwearingBinding

class Item_WearingFragment: Fragment() {
    lateinit var binding: FragmentItemwearingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemwearingBinding.inflate(inflater,container,false)
        return binding.root
    }

}