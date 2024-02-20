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
import com.umc.sculptor.data.model.remote.store.UserItem
import com.umc.sculptor.data.model.remote.store.ItemX
import com.umc.sculptor.data.model.remote.store.PurchasedItems
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
    var userItem : List<UserItem> = ArrayList<UserItem>()
    var wantedItems :List<ItemX>  = ArrayList<ItemX>()


    private  val information1 = arrayListOf("MY", "원석", "상품", "테마")
    private  val information2 = arrayListOf("나의 조각상", "착용중인 상품", "구매한 상품")

    // StoreFragment에서 사용할 인터페이스
    interface OnItemSelectListener {
        fun onItemSelected(userItem: UserItem)
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


        val call2: Call<PurchasedItems> = storeService.getPurchasedItems("JSESSIONID=" + LocalDataSource.getAccessToken().toString(), viewModel.selectedStatue.value?.id.toString())

        call2.enqueue(object : Callback<PurchasedItems> {
            override fun onResponse(call: Call<PurchasedItems>, response: Response<PurchasedItems>) {
                if (response.isSuccessful) {
                    userItem = response.body()?.data!!.userItems
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
            override fun onFailure(call2: Call<PurchasedItems>, t: Throwable) {
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
            "WORKOUT" -> R.drawable.work_b
            "STUDY" -> R.drawable.study_b
            "DAILY" -> R.drawable.daily_b
            else -> R.drawable.work_b //기본 이미지
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

        val viewPager1 = binding.ItemsContentVp
        val viewPager2 = binding.ItemsContentVp22

        val storeTabAdapter = StorePagerAdapter(this)
        val storeTabAdapter2 = StorePagerAdapter2(this)

        viewPager1.adapter = storeTabAdapter
        // TabLayout1 설정
        TabLayoutMediator(binding.tabLayout1, viewPager1) { tab, position ->
            tab.text = information1[position]
            Log.d("tab", "${tab.text}")
        }.attach()

        //22
        viewPager2.adapter = storeTabAdapter2
        // TabLayout2 설정
        TabLayoutMediator(binding.tabLayout2, viewPager2) { tab, position ->
            tab.text = information2[position]
            Log.d("tab", "${tab.text}")
        }.attach()

//탭레이아웃전환 수정필요...

// 탭 클릭에 따라서 ViewPager2의 가시성 조절
        binding.tabLayout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.ItemsContentVp.visibility = View.VISIBLE
                binding.ItemsContentVp22.visibility = View.INVISIBLE
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                // 다른 탭이 선택되면 아무 작업 없음
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
                // 다시 선택되면 아무 작업 없음
            }
        })

        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.ItemsContentVp.visibility = View.INVISIBLE
                binding.ItemsContentVp22.visibility = View.VISIBLE
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                // 다른 탭이 선택되면 아무 작업 없음
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
                // 다시 선택되면 아무 작업 없음
            }
        })

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


        viewModel.selectedStatue.observe(viewLifecycleOwner, Observer { selectedStatue -> binding.statueIv.setImageResource(changeImg(selectedStatue)) // 선택된 아이템의 이미지를 statueiv에 설정

        })

        viewModel.selectedUserItem.observe(viewLifecycleOwner, Observer { selectedItem ->
            Log.d("Observer", "selectedItem observed: $selectedItem")

            var itemIdToCheck = selectedItem.itemId

            // userItem에서 itemIdToCheck를 가진 UserItem 찾음
            var matchingItem = userItem.find { it.itemId == itemIdToCheck }

            if (matchingItem != null) {
                Log.d("Observer", "${matchingItem.itemId}")
                binding.saveBtnText.text = "저장"
                binding.SaveBtn.setBackgroundResource(R.drawable.store_savebtn)
            } else {
                binding.saveBtnText.text = "구매"
                binding.SaveBtn.setBackgroundResource(R.drawable.store_btn_clicked)
                Log.d("Observer", "구매 안 한 거 선택함")
            }
        })
        return binding.root
    }

}