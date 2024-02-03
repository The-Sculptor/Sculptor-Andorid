package com.umc.sculptor.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.R
import com.umc.sculptor.databinding.FragmentMystatueBinding
import com.umc.sculptor.databinding.StoreItemStatueBinding

class Item_MyStatueFragment: Fragment() {
    lateinit var binding: FragmentMystatueBinding
    private var itemDatas = ArrayList<Item>()
    private lateinit var itemRVAdapter: ItemRVAdapter
    private lateinit var viewModel: StoreViewModel

//    private lateinit var onItemSelectListener: StoreFragment.OnItemSelectListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMystatueBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

        //아이템 데이터
        itemDatas.apply {
            add(Item("sumin", R.drawable.bell, R.drawable.store_space))
            add(Item("sum", R.drawable.person, R.drawable.store_space))
            add(Item("in", R.drawable.store_statues, R.drawable.store_space))
            add(Item("sum", R.drawable.store_statues, R.drawable.store_space))
            add(Item("sumin", R.drawable.store_statues, R.drawable.store_space))
            add(Item("sum", R.drawable.store_statues, R.drawable.store_space))
        }

        val itemRVAdapter = ItemRVAdapter(itemDatas)
        binding.mystatueRv.adapter = itemRVAdapter

        itemRVAdapter.setMyItemClickListener(object : ItemRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                for (i in itemDatas.indices) {
                    val item = itemDatas[i]
                    if (i == position) {
                        item.backImg = R.drawable.store_space_picked

                        // 뷰모델을 통해 선택된 아이템 공유
                        viewModel.updateSelectedItem(item)


                    } else {
                        item.backImg = R.drawable.store_space
                    }
                }
                itemRVAdapter.notifyDataSetChanged()
            }
        })



        return binding.root
    }
}