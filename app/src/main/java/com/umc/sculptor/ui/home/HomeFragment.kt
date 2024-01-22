package com.umc.sculptor.ui.home

import android.util.Log
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.FriendStatue
import com.umc.sculptor.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    private lateinit var friendStatueAdapter: FriendStatueAdapter
    override fun initStartView() {
        super.initStartView()



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

    }


    override fun initAfterBinding() {
        super.initAfterBinding()

    }
}