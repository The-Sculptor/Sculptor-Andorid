package com.umc.sculptor.ui.store

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentStoreBinding

class StoreFragment : BaseFragment<FragmentStoreBinding>(R.layout.fragment_store) {

    private lateinit var itemRVAdapter: ItemRVAdapter

    private  val information = arrayListOf("나의 조각상", "착용중인 상품", "구매한 상품")

    // StoreFragment에서 사용할 인터페이스
    interface OnItemSelectListener {
        fun onItemSelected(item: Item)
    }

    // Item_MyStatueFragment에서 사용할 인터페이스
    interface MyItemClickListener {
        fun onItemCLick(position: Int)
    }

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()
    }

    private fun onSaveBtn() {
        binding.SaveBtn.setOnClickListener {
            binding.SaveBtn.setBackgroundResource(R.drawable.store_savebtn_clicked)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.SaveBtn.setBackgroundResource(R.drawable.store_savebtn)
            }, 1000)

            findNavController().navigate(R.id.action_storeFragment_to_itemListFragment)
            Log.d("saveBtn", "clicked")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)

        onSaveBtn()

        val storeTabAdapter = StorePagerAdapter(this)
        binding.ItemsContentVp.adapter = storeTabAdapter

        TabLayoutMediator(binding.tabLayout2, binding.ItemsContentVp){
            tab, position ->
            tab.text = information[position]
        }.attach()

        return binding.root
    }

}