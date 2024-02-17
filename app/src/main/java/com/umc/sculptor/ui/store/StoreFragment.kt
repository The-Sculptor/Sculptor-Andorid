package com.umc.sculptor.ui.store

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentStoreBinding

class StoreFragment : BaseFragment<FragmentStoreBinding>(R.layout.fragment_store) {

    private lateinit var viewModel: StoreViewModel
    private lateinit var itemRVAdapter: ItemRVAdapter

    private  val information = arrayListOf("나의 조각상", "착용중인 상품", "구매한 상품")

//    // StoreFragment에서 사용할 인터페이스
    interface OnItemSelectListener {
        fun onItemSelected(item: Item)
    }


    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).hideIconAndShowBack(false)
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()
    }

    private fun onSaveBtn() {
        binding.SaveBtn.setOnClickListener {
            binding.SaveBtn.setBackgroundResource(R.drawable.store_btn_clicked)
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



        // tabLayout2 설정
        TabLayoutMediator(binding.tabLayout2, binding.ItemsContentVp){
            tab, position ->
            tab.text = information[position]
        }.attach()


        // tabLayout1 설정
        TabLayoutMediator(binding.tabLayout1, binding.ItemsContentVp) {
                tab, position ->
                tab.text = information[position]
            tab.view.setOnClickListener {
                val adapterPosition = if (position == 2) position + 3 else position
                binding.ItemsContentVp.setCurrentItem(adapterPosition, true)
            }
        }.attach()


        binding.btnBeforeIv.setOnClickListener(){
            Log.d("BeforeBtn", "clicked")
        }

        binding.btnAfterIv.setOnClickListener(){
            Log.d("AfterBtn", "clicked")
        }

        binding.btnResetIv.setOnClickListener(){
            Log.d("ResetBtn", "clicked")
        }



        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

        viewModel.selectedStatue.observe(viewLifecycleOwner, Observer { selectedStatue -> // 뷰모델의 selectedItem을 observe하여 선택된 아이템이 변경되었을 때 호출되는 콜백 설정
            binding.statueIv.setImageResource(selectedStatue.statueImg ?: R.drawable.statue) // 선택된 아이템의 이미지를 statueiv에 설정

        })

        viewModel.selectedItem.observe(viewLifecycleOwner, Observer { selectedItem -> // 뷰모델의 selectedItem을 observe하여 선택된 아이템이 변경되었을 때 호출되는 콜백 설정
            if(selectedItem.buy == true) {
                binding.saveBtnText.text = "구매"
                binding.SaveBtn.setBackgroundResource(R.drawable.store_btn_clicked)
            } else {
                binding.saveBtnText.text = "저장"
                binding.SaveBtn.setBackgroundResource(R.drawable.store_savebtn)
            }

        })

        return binding.root
    }



}