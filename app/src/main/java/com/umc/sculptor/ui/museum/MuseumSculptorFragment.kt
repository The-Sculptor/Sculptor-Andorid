package com.umc.sculptor.ui.museum

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.databinding.FragmentMuseumSculptorBinding
import com.umc.sculptor.ui.home.FriendStatueAdapter

class MuseumSculptorFragment : BaseFragment<FragmentMuseumSculptorBinding>(R.layout.fragment_museum_sculptor) {

    private lateinit var museumCommentRVAdapter: MuseumCommentRVAdapter

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()
        val dumy : ArrayList<Comment> = ArrayList<Comment>()
        dumy.add(Comment(R.drawable.img_museum_sculptor_profile,"매트","너무 예쁘게 가꾸셨네요! 저도 따라 해봐야겠어요!!",true))
        dumy.add(Comment(R.drawable.img_museum_sculptor_profile,"클라라","저도 이렇게 하고 싶어요!",false))


        museumCommentRVAdapter= MuseumCommentRVAdapter(dumy)
        binding.museumSculptorCommentRv.adapter=museumCommentRVAdapter
        binding.museumSculptorCommentRv.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        binding.museumNewComment.setOnClickListener{
            val newComment=Comment(R.drawable.img_museum_sculptor_profile,"제로","너무 예뻐요~ 저는 언제쯤 이렇게 할 수 있을까요ㅜ",true)
            museumCommentRVAdapter.addItem(newComment)
        }

        museumCommentRVAdapter.setMyItemClickListener(object : MuseumCommentRVAdapter.MyItemClickListener {
            override fun onItemClick(position: Int) {

            }

        })


    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.museumSculptorInclude.museumSculptorImg.setOnClickListener {
            binding.museumSculptorInclude2.root.visibility = View.VISIBLE
            binding.museumSculptorInclude.root.visibility = View.GONE
        }
        binding.museumSculptorInclude2.museumIncludeBg.setOnClickListener{
            binding.museumSculptorInclude2.root.visibility = View.GONE
            binding.museumSculptorInclude.root.visibility = View.VISIBLE
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideIconAndShowBack(false)
    }

}