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
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.apiManager.ServicePool.storeService
import com.umc.sculptor.data.model.remote.store.Basket
import com.umc.sculptor.data.model.remote.store.ItemX
import com.umc.sculptor.data.model.remote.store.Stone
import com.umc.sculptor.data.model.remote.store.UserStones
import com.umc.sculptor.data.model.remote.store.WornItems
import com.umc.sculptor.databinding.FragmentStoreItemWearinglistBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemListFragment : Fragment(){
    lateinit var binding: FragmentStoreItemWearinglistBinding
    private var itemDatas: List<ItemX> = emptyList()
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

        var isAllItemsSelected = false
        var selectedItemCount = 0


        // ViewModel 초기화
        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

        val call: Call<Basket> = storeService.getBasket("JSESSIONID=" + LocalDataSource.getAccessToken().toString(), viewModel.selectedStatue.value?.id.toString())

        call.enqueue(object : Callback<Basket> {
            override fun onResponse(call: Call<Basket>, response: Response<Basket>) {
                if (response.isSuccessful) {
                    itemDatas = response.body()?.data?.items!!
                    if (itemDatas != null) {

                        // itemDatas를 사용하여 아이템으로 처리
                        itemListRVAdapter.itemList = itemDatas
                        itemListRVAdapter.notifyDataSetChanged()
                        Log.d("상점 서버", itemDatas.toString())
                    } else {
                        // 서버 응답에 오류가 있을 경우 처리
                        Log.d("상점 서버", "서버 응답 오류")
                    }
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("상점 서버", "서버 통신 오류")
                }
            }

            override fun onFailure(call: Call<Basket>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버 통신 실패 처리", t.message.toString())
            }
        })








        itemListRVAdapter = ItemListRVAdapter(itemDatas) { position, isSelected ->
            val item = itemDatas[position]
            //item.isSelected = isSelected

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
            //itemDatas.forEach { it.isSelected = isAllItemsSelected }

            // 선택된 아이템 수 업데이트
            selectedItemCount = if (isAllItemsSelected) itemDatas.size else 0
            binding.totalcheckCountTv.text = "전체 선택($selectedItemCount)"
            toggleCheckIcon(binding.totalcheckIv, isAllItemsSelected)

            itemListRVAdapter.notifyDataSetChanged()// 아이템 어댑터에 변경사항 알림
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