package com.umc.sculptor.ui.store

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.data.model.remote.store.PurchasedItems
import com.umc.sculptor.data.model.remote.store.UpdateWornItems
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.data.model.remote.store.UserItem
import com.umc.sculptor.databinding.FragmentItemboughtBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Item_BoughtFragment: Fragment() {
    lateinit var binding: FragmentItemboughtBinding
    private var itemDatas: List<UserItem> = emptyList()
    private lateinit var itemBoughtRVAdapter: ItemBoughtRVAdapter

    //private lateinit var onItemSelectListener: StoreFragment.OnItemSelectListener
    private lateinit var viewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemboughtBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)


        val call: Call<PurchasedItems> = ServicePool.storeService.getPurchasedItems("JSESSIONID=" + LocalDataSource.getAccessToken().toString(),viewModel.selectedStatue.value?.id.toString())

        call.enqueue(object : Callback<PurchasedItems> {
            override fun onResponse(call: Call<PurchasedItems>, response: Response<PurchasedItems>) {
                if (response.isSuccessful) {
                    itemDatas = response.body()?.data?.userItems!!


                    if (itemDatas != null) {

                        // itemDatas를 사용하여 아이템으로 처리
                        itemBoughtRVAdapter.itemList = itemDatas
                        itemBoughtRVAdapter.notifyDataSetChanged()
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

            override fun onFailure(call: Call<PurchasedItems>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버",t.message.toString())
            }
        })


        itemBoughtRVAdapter = ItemBoughtRVAdapter(itemDatas)
        binding.BoughtItemRv.adapter = itemBoughtRVAdapter




        itemBoughtRVAdapter.setMyItemClickListener(object : ItemBoughtRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                val clickedItemId = itemDatas[position].itemId

                val call: Call<UpdateWornItems> = ServicePool.storeService.updateWornItem("JSESSIONID=" + LocalDataSource.getAccessToken().toString(), viewModel.selectedStatue.value?.id.toString(), clickedItemId)

                call.enqueue(object : Callback<UpdateWornItems> {
                    override fun onResponse(call: Call<UpdateWornItems>, response: Response<UpdateWornItems>) {
                        if (response.isSuccessful) {
                            Log.d("상점 아이템 착용 서버", response.toString())
                            val updatedData = response.body()?.data?.stoneItems
                            if (updatedData != null) {
                                
                                val firstUpdatedItem = updatedData.first() // 첫 번째 아이템을 가져옴
                                viewModel.updateWornItem(firstUpdatedItem)

                                itemBoughtRVAdapter.notifyDataSetChanged()

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

                    override fun onFailure(call: Call<UpdateWornItems>, t: Throwable) {
                        // 통신 실패 처리
                        Log.d("상점 서버",t.message.toString())
                    }
                })
                itemBoughtRVAdapter.notifyDataSetChanged()
            }
        })


        return binding.root
    }
}