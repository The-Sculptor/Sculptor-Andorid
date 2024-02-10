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

    private fun toggleCheckIcon(imageView: ImageView) {
        val currentImage = imageView.drawable.constantState
        val newImage = if (currentImage == ContextCompat.getDrawable(requireContext(), R.drawable.icon_solid_check)?.constantState) {
            R.drawable.icon_outline_check
        } else {
            R.drawable.icon_solid_check
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

        itemListRVAdapter = ItemListRVAdapter(itemDatas)
        binding.storeWearingitemsRv.adapter = itemListRVAdapter

        
        binding.totalcheckIv.setOnClickListener(){// 체크 버튼 처리
            toggleCheckIcon(binding.totalcheckIv)
        }
        binding.havecheckIv.setOnClickListener(){
            toggleCheckIcon(binding.havecheckIv)
        }


        binding.storeBtn.setOnClickListener(){ //구매 스낵바
            val snackbar = Snackbar.make(
                binding.root,
                "구매가 완료되었습니다!",
                Snackbar.LENGTH_SHORT
            )
            snackbar.anchorView = binding.totalcheckIv
            snackbar.show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideIconAndShowBack(false)
    }
}