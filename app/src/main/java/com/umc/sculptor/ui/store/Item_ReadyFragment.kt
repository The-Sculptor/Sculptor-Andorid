package com.umc.sculptor.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.sculptor.databinding.FragmentItemReadyBinding

class Item_ReadyFragment : Fragment() {
    private lateinit var binding: FragmentItemReadyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemReadyBinding.inflate(inflater, container, false)
        return binding.root
    }
}