package com.umc.sculptor.ui.store

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.apiManager.ServicePool.storeService
import com.umc.sculptor.data.model.remote.store.Stone
import com.umc.sculptor.data.model.remote.store.UserStones
import com.umc.sculptor.databinding.FragmentMystatueBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Item_MyStatueFragment : Fragment() {
    private lateinit var binding: FragmentMystatueBinding
    private lateinit var itemRVAdapter: ItemRVAdapter
    private lateinit var viewModel: StoreViewModel
    private var itemDatas: List<Stone> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMystatueBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

        val call: Call<UserStones> = storeService.getMyStones("JSESSIONID=" + LocalDataSource.getAccessToken().toString())

        call.enqueue(object : Callback<UserStones> {
            override fun onResponse(call: Call<UserStones>, response: Response<UserStones>) {
                if (response.isSuccessful) {
                    itemDatas = response.body()?.data?.stones!!
                    if (itemDatas != null) {

                        // itemDatas를 사용하여 아이템으로 처리
                        itemRVAdapter.itemList = itemDatas
                        itemRVAdapter.notifyDataSetChanged()
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

            override fun onFailure(call: Call<UserStones>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버 통신 실패 처리", t.message.toString())
            }
        })

        itemRVAdapter = ItemRVAdapter(itemDatas)
        binding.mystatueRv.adapter = itemRVAdapter

        itemRVAdapter.setMyItemClickListener(object : ItemRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                var clickedItem = itemDatas[position]
                clickedItem.isSelected = !clickedItem.isSelected

                // 다른 아이템들은 선택 해제
                for (i in itemDatas.indices) {
                    if (i != position) {
                        val unselectedItem = itemDatas[i]
                        if (unselectedItem.isSelected) {
                            unselectedItem.isSelected = false // 클릭해서 토글
                            viewModel.updatereleasedStatue(unselectedItem)
                        }
                    }
                }
                if (clickedItem.isSelected) {
                    viewModel.updateSelectedStatue(clickedItem)
                } else {
                    viewModel.updatereleasedStatue(clickedItem)
                }

                // 클릭된 상태 확인
                val check = viewModel.selectedStatue.value
                Log.d("viewModelCheck", "$check")

                // 어댑터에 변경사항 알림
                itemRVAdapter.notifyDataSetChanged()
            }
        })


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

        val call: Call<UserStones> = storeService.getMyStones("JSESSIONID=" + LocalDataSource.getAccessToken().toString())

        call.enqueue(object : Callback<UserStones> {
            override fun onResponse(call: Call<UserStones>, response: Response<UserStones>) {
                if (response.isSuccessful) {
                    itemDatas = response.body()?.data?.stones!!
                    if (itemDatas != null) {

                        // itemDatas를 사용하여 아이템으로 처리
                        itemRVAdapter.itemList = itemDatas
                        itemRVAdapter.notifyDataSetChanged()
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

            override fun onFailure(call: Call<UserStones>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버 통신 실패 처리", t.message.toString())
            }
        })

        itemRVAdapter = ItemRVAdapter(itemDatas)
        binding.mystatueRv.adapter = itemRVAdapter

        itemRVAdapter.setMyItemClickListener(object : ItemRVAdapter.MyItemClickListener {
            override fun onItemCLick(position: Int) {
                var clickedItem = itemDatas[position]
                clickedItem.isSelected = !clickedItem.isSelected

                // 다른 아이템들은 선택 해제
                for (i in itemDatas.indices) {
                    if (i != position) {
                        val unselectedItem = itemDatas[i]
                        if (unselectedItem.isSelected) {
                            unselectedItem.isSelected = false // 클릭해서 토글
                            viewModel.updatereleasedStatue(unselectedItem)
                        }
                    }
                }
                if (clickedItem.isSelected) {
                    viewModel.updateSelectedStatue(clickedItem)
                } else {
                    viewModel.updatereleasedStatue(clickedItem)
                }

                // 클릭된 상태 확인
                val check = viewModel.selectedStatue.value
                Log.d("viewModelCheck", "$check")

                // 어댑터에 변경사항 알림
                itemRVAdapter.notifyDataSetChanged()
            }
        })

    }
}
