package com.umc.sculptor.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.databinding.FragmentItemwearingBinding

class Item_WearingFragment: Fragment() {
    lateinit var binding: FragmentItemwearingBinding
    private var itemDatas:List<Item> = emptyList()
    private lateinit var itemWearingRVAdapter: ItemWearingRVAdapter

    private lateinit var viewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemwearingBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)





        itemWearingRVAdapter = ItemWearingRVAdapter(itemDatas)
        binding.itemwearingRv.adapter = itemWearingRVAdapter


        itemWearingRVAdapter.setMyItemClickListener(object : ItemWearingRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                for (i in itemDatas.indices) {
                    val item = itemDatas[i]
                    if (i == position) {
                        //item.backImg = R.drawable.store_wearing_item_r_selected
                        //viewModel.updateSelectedItem_item(item)
                    } else {
                        //item.backImg = R.drawable.store_wearingitem_r
                    }
                }
                itemWearingRVAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

}