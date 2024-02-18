package com.umc.sculptor.ui.store

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.apiManager.ServicePool.storeService
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.data.model.remote.store.StoreItems
import com.umc.sculptor.databinding.FragmentItemwearingBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Item_StoneFragment: Fragment() {
    lateinit var binding: FragmentItemwearingBinding
    private var itemDatas: List<Item> = emptyList()
    private lateinit var stoneRVAdapter: StoneRVAdapter

    private lateinit var viewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemwearingBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)


        val call: Call<StoreItems> = storeService.getStoreItems("JSESSIONID=" + LocalDataSource.getAccessToken().toString())

        call.enqueue(object : Callback<StoreItems> {
            override fun onResponse(call: Call<StoreItems>, response: Response<StoreItems>) {
                if (response.isSuccessful) {
                    itemDatas = response.body()?.data?.items!!
                    if (itemDatas != null) {

                        // itemDatas를 사용하여 아이템으로 처리
                        stoneRVAdapter.itemList = itemDatas
                        stoneRVAdapter.notifyDataSetChanged()
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

            override fun onFailure(call: Call<StoreItems>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버",t.message.toString())
            }
        })


        stoneRVAdapter = StoneRVAdapter(itemDatas)
        binding.itemwearingRv.adapter = stoneRVAdapter


        stoneRVAdapter.setMyItemClickListener(object : StoneRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                for (i in itemDatas.indices) {
                    val item = itemDatas[i]
                    if (i == position) {
                        item.isSelected = true
                        viewModel.updateSelectedItem_item(item)
                    } else {
                        item.isSelected = false
                        viewModel.updatereleasedItem_item(item)
                    }
                }
                stoneRVAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

}