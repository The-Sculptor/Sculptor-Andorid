package com.umc.sculptor.ui.museum

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentMuseumProfileMeBinding

class MuseumProfileMeFragment : BaseFragment<FragmentMuseumProfileMeBinding>(R.layout.fragment_museum_profile_me) {

    private lateinit var museumSculptorRVAdapter: MuseumSculptorRVAdapter
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).hideIconAndShowBack(false)


    }

    override fun initDataBinding() {
        super.initDataBinding()
        val dumy : ArrayList<Sculptor> = ArrayList<Sculptor>()
        dumy.add(Sculptor("D+66","조깅하기","2023.06.13",R.drawable.img_museum_sculptor))
        dumy.add(Sculptor("D+33","아침먹기","2023.03.03",R.drawable.img_museum_sculptor))
        dumy.add(Sculptor("D+12","일기쓰기","2023.10.20",R.drawable.img_museum_sculptor))

        museumSculptorRVAdapter= MuseumSculptorRVAdapter(dumy)
        binding.museumProfileSculptorRv.adapter=museumSculptorRVAdapter
        binding.museumProfileSculptorRv.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        museumSculptorRVAdapter.setMyItemClickListener(object: MuseumSculptorRVAdapter.MyItemClickListener{
            override fun onItemClick(sculptor: Sculptor) {
                navController.navigate(R.id.action_museumProfileMeFragment_to_museumSculptorFragment)
            }

        })


    }

    override fun initAfterBinding() {
        super.initAfterBinding()
        binding.museumBtnWrite.setOnClickListener{
            navController.navigate(R.id.action_museumProfileMeFragment_to_museumEditFragment)
        }

    }

}