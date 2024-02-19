package com.umc.sculptor.ui.store

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool.storeService
import com.umc.sculptor.data.model.remote.store.Basket
import com.umc.sculptor.data.model.remote.store.ItemX
import com.umc.sculptor.databinding.FragmentStoreItemWearinglistBinding
import com.umc.sculptor.login.LocalDataSource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
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
    fun List<String>?.toJsonString(): String {
        val jsonArray = JsonArray()
        this?.forEach { itemId ->
            jsonArray.add(itemId)
        }
        val jsonObject = JsonObject().apply {
            add("itemIds", jsonArray)
        }
        return jsonObject.toString()
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
        var selectedItemPowder = 0


        // ViewModel 초기화
        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

        val itemIds = viewModel._selectedItemsList.value?.toJsonString() ?: "[]"
        val mediaType = "application/json".toMediaTypeOrNull()
        val requestBody = RequestBody.create(mediaType, itemIds)
        val call: Call<Basket> = storeService.getBasket("JSESSIONID=" + LocalDataSource.getAccessToken().toString(), viewModel.selectedStatue.value?.id.toString(), requestBody)

        call.enqueue(object : Callback<Basket> {
            override fun onResponse(call: Call<Basket>, response: Response<Basket>) {
                if (response.isSuccessful) {
                    itemDatas = response.body()?.data?.items!!

                    // itemDatas를 사용하여 아이템으로 처리
                    itemListRVAdapter.itemList = itemDatas
                    itemListRVAdapter.notifyDataSetChanged()
                    Log.d("상점 서버", itemDatas.toString())
                } else {
                    // 서버 응답에 오류가 있을 경우 처리
                    Log.d("상점 서버", "서버 응답 오류-${response.code()}")
                }
            }
            override fun onFailure(call: Call<Basket>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버 통신 실패 처리", t.message.toString())
            }
        })


        itemListRVAdapter = ItemListRVAdapter(itemDatas)
        binding.storeWearingitemsRv.adapter = itemListRVAdapter
        itemListRVAdapter.setMyItemClickListener(object : ItemListRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                val clickedItem = itemDatas[position]

                Log.d("listitemitemitem", "${clickedItem.id}")
                if (clickedItem.isChecked) {
                    // 이미 선택된 아이템을 다시 클릭한 경우, 선택 해제
                    clickedItem.isChecked = false
                    viewModel.removeCheckedListItemId(clickedItem.id)
                    selectedItemCount--
                    selectedItemPowder -=clickedItem.price
                } else {
                    // 새로운 아이템을 선택한 경우, 선택 추가
                    clickedItem.isChecked = true
                    viewModel.addCheckedListItemId(clickedItem.id)
                    selectedItemCount++
                    selectedItemPowder +=clickedItem.price
                }
                Log.d("itemwishListChecked", "${viewModel._checkedListItemsList.value}")

                isAllItemsSelected = selectedItemCount == itemDatas.size
                binding.buyBtnTv.text = "구매하기 (${selectedItemCount})" //구매 버튼 내 선택된 아이템 수 UI 업데이트

                binding.totalcheckCountTv.text = "전체 선택($selectedItemCount)" // 선택된 아이템 수 UI 업데이트
                binding.stonepowderG.text = "${selectedItemPowder}g"

                toggleCheckIcon(binding.totalcheckIv, isAllItemsSelected)
                itemListRVAdapter.notifyDataSetChanged()
            }
        })


        binding.totalcheckIv.setOnClickListener(){// 전체 선택 체크 버튼 처리
            isAllItemsSelected = !isAllItemsSelected
            if (!isAllItemsSelected) {
                selectedItemCount = 0 // 전체 선택 해제 시 선택된 아이템 수 초기화
                selectedItemPowder = 0
            }
            // 전체 아이템 선택 상태 업데이트
            itemDatas.forEach {
                it.isChecked = isAllItemsSelected
                if (isAllItemsSelected) {
                    selectedItemPowder += it.price // 전체 선택 시 가격 누적
                } else {
                    selectedItemPowder = 0 // 전체 선택 해제 시 가격 초기화
                }
            }

            // 선택된 아이템 수 업데이트
            selectedItemCount = if (isAllItemsSelected) itemDatas.size else 0
            binding.totalcheckCountTv.text = "전체 선택($selectedItemCount)"
            binding.buyBtnTv.text = "구매하기 (${selectedItemCount})" //구매 버튼 내 선택된 아이템 수 UI 업데이트
            binding.stonepowderG.text = "${selectedItemPowder}g"
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