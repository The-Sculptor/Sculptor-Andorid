package com.umc.sculptor.ui.store

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool.storeService
import com.umc.sculptor.data.model.remote.store.Data
import com.umc.sculptor.data.model.remote.store.Stone
import com.umc.sculptor.data.model.remote.store.UserStones
import com.umc.sculptor.databinding.FragmentMystatueBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Item_MyStatueFragment: Fragment() {
    lateinit var binding: FragmentMystatueBinding
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


        var itemDatas : List<Stone> = ArrayList<Stone>()
        val call: Call<UserStones> = storeService.getMyStones("JSESSIONID="+ LocalDataSource.getAccessToken().toString())

        call.enqueue(object : Callback<UserStones> {
            override fun onResponse(call: Call<UserStones>, response: Response<UserStones>) {
                if (response.isSuccessful) {
                    itemDatas = response.body()?.data ?: ArrayList<Stone>()
                    itemRVAdapter.itemList = itemDatas
                    itemRVAdapter.notifyDataSetChanged()
                    Log.d("상점 서버",itemDatas.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("상점 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<UserStones>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버",t.message.toString())
            }
        })







        val itemRVAdapter = ItemRVAdapter(itemDatas)
        binding.mystatueRv.adapter = itemRVAdapter

        itemRVAdapter.setMyItemClickListener(object : ItemRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                for (i in itemDatas.indices) {
                    val item = itemDatas[i]
                    if (i == position) {
//                        item.backImg = R.drawable.store_space_picked
//
//                        // 뷰모델을 통해 선택된 아이템 공유
//                        viewModel.updateSelectedItem(item)

                    } else {
//                        item.backImg = R.drawable.store_space
                    }
                }
                itemRVAdapter.notifyDataSetChanged()
            }
        })



        return binding.root
    }
}