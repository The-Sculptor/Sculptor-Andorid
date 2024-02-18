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
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool.storeService
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.remote.store.Basket
import com.umc.sculptor.data.model.remote.store.DataXX
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.data.model.remote.store.ItemX
import com.umc.sculptor.data.model.remote.store.Stone
import com.umc.sculptor.data.model.remote.store.UserMoney
import com.umc.sculptor.databinding.FragmentStoreBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreFragment : BaseFragment<FragmentStoreBinding>(R.layout.fragment_store) {

    private lateinit var viewModel: StoreViewModel
    private lateinit var itemRVAdapter: ItemRVAdapter
    var userMoney : List<DataXX> =ArrayList<DataXX>()
    var userItem : List<ItemX> = ArrayList<ItemX>()


    private  val information1 = arrayListOf("MY", "원석", "상품", "테마")
    private  val information2 = arrayListOf("나의 조각상", "착용중인 상품", "구매한 상품")

    // StoreFragment에서 사용할 인터페이스
    interface OnItemSelectListener {
        fun onItemSelected(item: Item)
    }


    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
    }

    override fun initDataBinding() {
        super.initDataBinding()


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


        val call2: Call<Basket> = storeService.getBasket("JSESSIONID=" + LocalDataSource.getAccessToken().toString(), viewModel.selectedStatue.value?.id.toString())

        call2.enqueue(object : Callback<Basket> {
            override fun onResponse(call: Call<Basket>, response: Response<Basket>) {
                if (response.isSuccessful) {
                    userItem = response.body()?.data?.items!!
                    if (userItem != null) {

                        Log.d("상점 서버", userItem.toString())
                    } else {
                        // 서버 응답에 오류가 있을 경우 처리
                        Log.d("상점 서버", "서버 응답 오류")
                    }
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("상점 서버", "서버 통신 오류")
                }
            }
            override fun onFailure(call2: Call<Basket>, t: Throwable) {
                // 통신 실패 처리
                Log.d("상점 서버 통신 실패 처리", t.message.toString())
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


    fun changeImg(stone: Stone): Int {
        val resourceId = when (stone.category) {
            "WORKOUT" -> R.drawable.stone_workoutimg
            "STUDY" -> R.drawable.stone_studyimg
            "DAILY" -> R.drawable.stone_dailyimg
            else -> R.drawable.statue //기본 이미지
        }
        Log.d("ChangeImg", "Category: ${stone.category}, Resource ID: $resourceId")
        return resourceId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)

        onSaveBtn()

        val sharedViewPager = binding.ItemsContentVp

        val storeTabAdapter = StorePagerAdapter(this)
        val storeTabAdapter2 = StorePagerAdapter2(this)

        sharedViewPager.adapter = storeTabAdapter
        // TabLayout1 설정
        TabLayoutMediator(binding.tabLayout1, sharedViewPager) { tab, position ->
            tab.text = information1[position]
            Log.d("tab", "${tab.text}")
        }.attach()

//        sharedViewPager.adapter = storeTabAdapter2
//        // TabLayout2 설정
//        TabLayoutMediator(binding.tabLayout2, sharedViewPager) { tab, position ->
//            tab.text = information2[position]
//            Log.d("tab", "${tab.text}")
//        }.attach()

//탭레이아웃전환 수정필요...



        binding.btnBeforeIv.setOnClickListener(){
            Log.d("BeforeBtn", "clicked")
        }

        binding.btnAfterIv.setOnClickListener(){
            Log.d("AfterBtn", "clicked")
        }

        binding.btnResetIv.setOnClickListener(){
            Log.d("ResetBtn", "clicked")
        }



        viewModel = ViewModelProvider(this).get(StoreViewModel::class.java)


        viewModel.selectedStatue.observe(viewLifecycleOwner, Observer { selectedStatue ->
           binding.statueIv.setImageResource(changeImg(selectedStatue)) // 선택된 아이템의 이미지를 statueiv에 설정

        })

        viewModel.selectedItem.observe(viewLifecycleOwner, Observer { selectedItem ->
            Log.d("Observer", "selectedItem observed: $selectedItem")

            var itemIdToCheck = selectedItem.itemId

            // userItem에서 itemIdToCheck를 가진 ItemX를 찾음
            var matchingItem = userItem.find { it.id == itemIdToCheck }

            if (matchingItem != null) {
                Log.d("Observer", "${matchingItem.id}")

                if (!matchingItem.isPurchased) {
                    binding.saveBtnText.text = "구매"
                    binding.SaveBtn.setBackgroundResource(R.drawable.store_btn_clicked)
                } else {
                    binding.saveBtnText.text = "저장"
                    binding.SaveBtn.setBackgroundResource(R.drawable.store_savebtn)
                }
            } else {
                // matchingItem이 null인 경우 처리
                Log.d("Observer", "matchingItem is null")
            }
        })



        return binding.root
    }

}