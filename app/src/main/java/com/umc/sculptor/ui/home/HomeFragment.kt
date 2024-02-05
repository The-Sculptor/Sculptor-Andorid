package com.umc.sculptor.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.FriendStatue
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.ui.workshop.BoxAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    private lateinit var friendStatueAdapter: FriendStatueAdapter
    @SuppressLint("ResourceAsColor")
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).binding.mainLogo.visibility = View.VISIBLE
        (activity as MainActivity).hideIconAndShowBack(false)

    }

    override fun initDataBinding() {
        super.initDataBinding()

        val dumy : ArrayList<FriendStatue> =ArrayList<FriendStatue>()
        dumy.add(FriendStatue("SONG"))
        dumy.add(FriendStatue("SONG"))
        dumy.add(FriendStatue("SONG"))
        dumy.add(FriendStatue("SONG"))

        friendStatueAdapter = FriendStatueAdapter(dumy)
        binding.rvFriendStatue.adapter = friendStatueAdapter
        binding.rvFriendStatue.layoutManager = LinearLayoutManager(context)


        // 아이템 클릭 리스너 설정
        friendStatueAdapter.setOnItemClickListener(object : FriendStatueAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                navController.navigate(R.id.action_homeFragment_to_friendStoneFragment)
            }
        })

    }


    override fun initAfterBinding() {
        super.initAfterBinding()


        binding.etProfileSearch.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_searchFragment)
        }

    }

    @SuppressLint("ResourceAsColor")
    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).binding.mainLogo.visibility = View.GONE
    }
}