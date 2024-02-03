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
import com.umc.sculptor.databinding.FragmentMystatueBinding

class Item_WearingFragment: Fragment() {
    lateinit var binding: FragmentItemwearingBinding
    private var itemDatas = ArrayList<Item>()
    private lateinit var itemRVAdapter: ItemRVAdapter

    private lateinit var onItemSelectListener: StoreFragment.OnItemSelectListener


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemwearingBinding.inflate(inflater,container,false)


        //아이템 데이터
        itemDatas.apply {
            add(Item("wearing", R.drawable.bell, R.drawable.store_wearingitem_r))
            add(Item("sum", R.drawable.bell, R.drawable.store_wearingitem_r))
           add(Item("sumin", R.drawable.person, R.drawable.store_wearingitem_r))
            add(Item("sum", R.drawable.person, R.drawable.store_wearingitem_r))
        }


        val itemRVAdapter = ItemRVAdapter(itemDatas)
        binding.itemwearingRv.adapter = itemRVAdapter

        itemRVAdapter.setMyItemClickListener(object : ItemRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                for (i in itemDatas.indices) {
                    val item = itemDatas[i]
                    if (i == position) {
                        item.backImg = R.drawable.store_wearing_item_r_selected


                    } else {
                        item.backImg = R.drawable.store_wearingitem_r
                    }
                }
                itemRVAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

}