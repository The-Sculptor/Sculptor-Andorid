package com.umc.sculptor.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.databinding.FragmentItemboughtBinding

class Item_BoughtFragment: Fragment() {
    lateinit var binding: FragmentItemboughtBinding
    private var itemDatas: List<Item> = emptyList()
    private lateinit var itemWearingRVAdapter: ItemWearingRVAdapter

    private lateinit var onItemSelectListener: StoreFragment.OnItemSelectListener
    private lateinit var viewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemboughtBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

//        //아이템 데이터
//        itemDatas.apply {
//            add(Item_WB("bought", R.drawable.bell, R.drawable.store_wearingitem_r,11))
//            add(Item_WB("sum", R.drawable.bell, R.drawable.store_wearingitem_r,22))
//            add(Item_WB("min", R.drawable.person, R.drawable.store_wearingitem_r,33))
//            add(Item_WB("susu", R.drawable.person, R.drawable.store_wearingitem_r,44,true))
//        }



        //val itemWearingRVAdapter = ItemWearingRVAdapter(itemDatas)
        binding.BoughtItemRv.adapter = itemWearingRVAdapter

        itemWearingRVAdapter.setMyItemClickListener(object : ItemWearingRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                for (i in itemDatas.indices) {
                    val item = itemDatas[i]
                    if (i == position) {
                        //item.backImg = R.drawable.store_wearing_item_r_selected


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