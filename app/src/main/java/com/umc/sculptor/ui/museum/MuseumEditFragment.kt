package com.umc.sculptor.ui.museum

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentMuseumEditBinding

class MuseumEditFragment : BaseFragment<FragmentMuseumEditBinding>(R.layout.fragment_museum_edit){

    private lateinit var museumEditRVAdapter: MuseumEditRVAdapter
    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()
        val dumy : ArrayList<Edit> = ArrayList<Edit>()
        dumy.add(Edit("D+66","조깅하기"))
        dumy.add(Edit("D+33","아침먹기"))
        dumy.add(Edit("D+12","일기쓰기"))

        museumEditRVAdapter= MuseumEditRVAdapter(dumy)
        binding.museumSculptorCommentRv.adapter=museumEditRVAdapter
        binding.museumSculptorCommentRv.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

    }


    override fun initAfterBinding() {
        super.initAfterBinding()
        binding.museumBackBtn.setOnClickListener{
            //onbackpressed가 안되서 대체
            binding.museumBackBtn.setOnClickListener{
                navController.navigate(R.id.action_museumEditFragment_to_museumProfileMeFragment)
            }
        }

    }
}
