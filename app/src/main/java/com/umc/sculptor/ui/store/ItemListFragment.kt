package com.umc.sculptor.ui.store

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.databinding.FragmentStoreItemWearinglistBinding

class ItemListFragment : Fragment(){
    lateinit var binding: FragmentStoreItemWearinglistBinding
    private var itemDatas = ArrayList<Item_WB>()
    private lateinit var itemListRVAdapter: ItemListRVAdapter
    private lateinit var viewModel: StoreViewModel

    private fun toggleCheckIcon(imageView: ImageView, isTrue: Boolean) {
        val currentImage = imageView.drawable.constantState
        val newImage = if (isTrue) {
            R.drawable.icon_solid_check
        } else {
            R.drawable.icon_outline_check
        }
        imageView.setImageResource(newImage)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentStoreItemWearinglistBinding.inflate(inflater, container,false)
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)

        itemDatas.apply {
            add(Item_WB("item1", R.drawable.bell,  R.drawable.store_wearingitem_r, 1, true))
            add(Item_WB("item2", R.drawable.person,  R.drawable.store_wearingitem_r, 2, true))

        }


        var isAllItemsSelected = false
        var selectedItemCount = 0

        itemListRVAdapter = ItemListRVAdapter(itemDatas) { position, isSelected ->
            val item = itemDatas[position]
            item.isSelected = isSelected


            // 선택된 아이템 수 업데이트
            if (isSelected) {
                selectedItemCount++
            } else{
                selectedItemCount--
            }
            isAllItemsSelected = selectedItemCount == itemDatas.size

            binding.buyBtnTv.text = "구매하기 (${selectedItemCount})" //구매 버튼 내 선택된 아이템 수 UI 업데이트

            binding.totalcheckCountTv.text = "전체 선택($selectedItemCount)" // 선택된 아이템 수 UI 업데이트
            toggleCheckIcon(binding.totalcheckIv, isAllItemsSelected)
            itemListRVAdapter.notifyItemChanged(position)
        }
        binding.storeWearingitemsRv.adapter = itemListRVAdapter


        binding.totalcheckIv.setOnClickListener(){// 전체 선택 체크 버튼 처리
            isAllItemsSelected = !isAllItemsSelected
            if (!isAllItemsSelected) {
                selectedItemCount = 0 // 전체 선택 해제 시 선택된 아이템 수 초기화
            }
            // 전체 아이템 선택 상태 업데이트
            itemDatas.forEach { it.isSelected = isAllItemsSelected }

            // 선택된 아이템 수 업데이트
            selectedItemCount = if (isAllItemsSelected) itemDatas.size else 0
            binding.totalcheckCountTv.text = "전체 선택($selectedItemCount)"
            toggleCheckIcon(binding.totalcheckIv, isAllItemsSelected)

            itemListRVAdapter.notifyDataSetChanged()// 아이템 어댑터에 변경사항 알림
        }


        var haveCeck = false
        binding.havecheckIv.setOnClickListener(){
            haveCeck = !haveCeck
            toggleCheckIcon(binding.havecheckIv,haveCeck)
        }


        binding.storeBtn.setOnClickListener(){ //구매 스낵바
            val snackbar = Snackbar.make(
                binding.root,
                "구매가 완료되었습니다!",
                Snackbar.LENGTH_SHORT
            )
            snackbar.anchorView = binding.havecheckIv
            snackbar.show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideIconAndShowBack(false)
    }
}