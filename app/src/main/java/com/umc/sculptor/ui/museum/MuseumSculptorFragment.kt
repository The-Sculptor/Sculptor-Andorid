package com.umc.sculptor.ui.museum

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentMuseumSculptorBinding

class MuseumSculptorFragment : BaseFragment<FragmentMuseumSculptorBinding>(R.layout.fragment_museum_sculptor) {

    private lateinit var museumCommentRVAdapter: MuseumCommentRVAdapter
    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()
        val dumy : ArrayList<Comment> = ArrayList<Comment>()
        dumy.add(Comment(R.drawable.img_museum_sculptor_profile,"매트","너무 예쁘게 가꾸셨네요! 저도 따라 해봐야겠어요!!"))
        dumy.add(Comment(R.drawable.img_museum_sculptor_profile,"클라라","저도 이렇게 하고 싶어요!"))


        museumCommentRVAdapter= MuseumCommentRVAdapter(dumy)
        binding.museumSculptorCommentRv.adapter=museumCommentRVAdapter
        binding.museumSculptorCommentRv.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.museumSculptorInclude.museumSculptorImg.setOnClickListener {
            binding.museumSculptorInclude2.root.visibility = View.VISIBLE
            binding.museumSculptorInclude.root.visibility = View.GONE
        }


    }

}