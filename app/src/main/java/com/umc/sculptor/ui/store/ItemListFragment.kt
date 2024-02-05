package com.umc.sculptor.ui.store

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.umc.sculptor.R
import com.umc.sculptor.databinding.FragmentStoreBinding
import com.umc.sculptor.databinding.FragmentStoreItemWearinglistBinding

class ItemListFragment : Fragment(){
    lateinit var binding: FragmentStoreItemWearinglistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreItemWearinglistBinding.inflate(inflater, container,false)

        binding.backIcon.setOnClickListener{
            findNavController().navigate(R.id.action_itemListFragment_to_storeFragment)
            Log.d("backIcon", "clicked")
        }



        return binding.root
    }
}