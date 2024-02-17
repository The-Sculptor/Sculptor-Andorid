package com.umc.sculptor.ui.store

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool.storeService
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.remote.store.DataXX
import com.umc.sculptor.data.model.remote.store.UserMoney
import com.umc.sculptor.databinding.FragmentStoreBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    }

    override fun initDataBinding() {
        super.initDataBinding()

        var userMoney : List<DataXX> =ArrayList<DataXX>()

        val call: Call<UserMoney> = storeService.getMoney("JSESSIONID="+ LocalDataSource.getAccessToken().toString())

        call.enqueue(object : Callback<UserMoney> {
            override fun onResponse(call: Call<UserMoney>, response: Response<UserMoney>) {
                if (response.isSuccessful) {
                    var userMoney: UserMoney? = response.body()
                    userMoney?.let {
                        val howMuchValue = it.data.totalPowder.toString() // totalPowder 값을 가져옴
                        binding.howmuchTv.text = "$howMuchValue" // 가져온 값을 TextView에 설정
                    }
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    binding.howmuchTv.text = "서버 오류"
                }
            }

            override fun onFailure(call: Call<UserMoney>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버",t.message.toString())
            }
        })

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


//
//// TabLayout2 설정
//        TabLayoutMediator(binding.tabLayout2, binding.ItemsContentVp) { tab, position ->
//            val index = position % 3 // TabLayout2의 탭 수는 3개
//            tab.text = information[index]
//        }.attach()
//
//// TabLayout1 설정
//        TabLayoutMediator(binding.tabLayout1, binding.ItemsContentVp) { tab, position ->
//            tab.text = information[position]
//        }.attach()




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