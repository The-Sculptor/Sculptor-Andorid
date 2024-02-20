package com.umc.sculptor.ui.store

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.data.model.remote.store.DataXXXX
import com.umc.sculptor.data.model.remote.store.StoneItemX
import com.umc.sculptor.data.model.remote.store.WornItems
import com.umc.sculptor.databinding.FragmentItemwearingBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Item_WearingFragment: Fragment() {
    lateinit var binding: FragmentItemwearingBinding
    private var itemDatas:ArrayList<DataXXXX> = ArrayList()
    private lateinit var itemWearingRVAdapter: ItemWearingRVAdapter

    private lateinit var viewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemwearingBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

        val call: Call<WornItems> = ServicePool.storeService.getWornItems("JSESSIONID=" + LocalDataSource.getAccessToken().toString(), viewModel.selectedStatue.value?.id.toString())

        call.enqueue(object : Callback<WornItems> {
            override fun onResponse(call: Call<WornItems>, response: Response<WornItems>) {
                if (response.isSuccessful) {

                    response.body()?.data?.let { itemDatas.add(it) }
                    // itemDatas를 사용하여 아이템으로 처리
                    itemWearingRVAdapter.itemList = itemDatas
                    itemWearingRVAdapter.notifyDataSetChanged()
                    Log.d("상점 서버", itemDatas.toString())

                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("상점 서버", "서버 통신 오류")
                }
            }


            override fun onFailure(call: Call<WornItems>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버 통신 실패 처리", t.message.toString())
            }
        })


        itemWearingRVAdapter = ItemWearingRVAdapter(itemDatas)
        binding.itemwearingRv.adapter = itemWearingRVAdapter



        itemWearingRVAdapter.setMyItemClickListener(object : ItemWearingRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                for (i in itemDatas.indices) {
                    val item = itemDatas[i]
                    if (i == position) {
                        //item.backImg = R.drawable.store_wearing_item_r_selected
                        //viewModel.updateSelectedItem_item(item)
                    } else {
                        //item.backImg = R.drawable.store_wearingitem_r
                    }
                }
                itemWearingRVAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

        itemDatas = ArrayList<DataXXXX>()

        val call: Call<WornItems> = ServicePool.storeService.getWornItems("JSESSIONID=" + LocalDataSource.getAccessToken().toString(), viewModel.selectedStatue.value?.id.toString())

        call.enqueue(object : Callback<WornItems> {
            override fun onResponse(call: Call<WornItems>, response: Response<WornItems>) {
                if (response.isSuccessful) {

                    response.body()?.data?.let { itemDatas.add(it) }
                    // itemDatas를 사용하여 아이템으로 처리
                    itemWearingRVAdapter.itemList = itemDatas
                    itemWearingRVAdapter.notifyDataSetChanged()
                    Log.d("상점 서버", itemDatas.toString())

                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("상점 서버", "서버 통신 오류")
                }
            }


            override fun onFailure(call: Call<WornItems>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버 통신 실패 처리", t.message.toString())
            }
        })


        itemWearingRVAdapter = ItemWearingRVAdapter(itemDatas)
        binding.itemwearingRv.adapter = itemWearingRVAdapter


        itemWearingRVAdapter.setMyItemClickListener(object : ItemWearingRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                for (i in itemDatas.indices) {
                    val item = itemDatas[i]
                    if (i == position) {
                        //item.backImg = R.drawable.store_wearing_item_r_selected
                        //viewModel.updateSelectedItem_item(item)
                    } else {
                        //item.backImg = R.drawable.store_wearingitem_r
                    }
                }
                itemWearingRVAdapter.notifyDataSetChanged()
            }
        })
    }
}