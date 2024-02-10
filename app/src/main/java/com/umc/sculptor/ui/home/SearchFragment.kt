package com.umc.sculptor.ui.home

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.FriendMuseumViewModel
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.data.model.remote.home.DataX
import com.umc.sculptor.data.model.remote.home.ResearchResponseDto
import com.umc.sculptor.databinding.FragmentSearchBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var searchAdapter: SearchAdapter
    val viewModel: FriendMuseumViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FriendMuseumViewModel::class.java)
    }

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()


        val itemList : List<DataX> =ArrayList<DataX>()
//        dumy.add(Alarm("SONG"))
//        dumy.add(Alarm("NICKNAME"))


        searchAdapter = SearchAdapter(itemList,requireContext())
        binding.rvSearchResult.adapter = searchAdapter
        binding.rvSearchResult.layoutManager = LinearLayoutManager(context)

        // 아이템 클릭 리스너 설정
        searchAdapter.setOnItemClickListener(object : SearchAdapter.OnItemClickListener {
            override fun onItemClick(id: String) {
                viewModel.message.value = id
                navController.navigate(R.id.action_searchFragment_to_museumProfileOtherFragment)
            }
        })

    }


    override fun initAfterBinding() {
        super.initAfterBinding()


        binding.etProfileSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변화가 시작될 때
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트에 변화가 있을 때
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변화가 끝났을 때
                // 서버 통신 요청
                val call: Call<ResearchResponseDto> = ServicePool.homeService.search(
                    "JSESSIONID="+ LocalDataSource.getAccessToken().toString(),
                    binding.etProfileSearch.text.toString())

                // 비동기적으로 요청 수행
                call.enqueue(object : Callback<ResearchResponseDto> {
                    override fun onResponse(call: Call<ResearchResponseDto>, response: Response<ResearchResponseDto>) {
                        if (response.isSuccessful) {
                            val itemList = response.body()?.data?: ArrayList<DataX>()
                            Log.d("검색 서버",itemList.toString())
                            searchAdapter.updateItemList(itemList)
                        } else {
                            // 서버에서 오류 응답을 받은 경우 처리
                            Log.d("검색 서버","서버통신 오류")
                        }
                    }

                    override fun onFailure(call: Call<ResearchResponseDto>, t: Throwable) {
                        // 통신 실패 처리
                        Log.d("검색 서버",t.message.toString())
                    }
                })
            }
        })

        binding.ivCancel.setOnClickListener {
            binding.etProfileSearch.setText("")
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}