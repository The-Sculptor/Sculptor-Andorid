package com.umc.sculptor.ui.museum

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capjjang.rightnow.api.MuseumService
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Category
import com.umc.sculptor.data.model.dto.MuseumDetail
import com.umc.sculptor.data.model.dto.MuseumDetailViewModel
import com.umc.sculptor.data.model.remote.museum.Comment
import com.umc.sculptor.data.model.remote.museum.Comments
import com.umc.sculptor.data.model.remote.museum.Museum
import com.umc.sculptor.data.model.remote.museum.Stone
import com.umc.sculptor.databinding.FragmentMuseumSculptorBinding
import com.umc.sculptor.login.LocalDataSource
import com.umc.sculptor.ui.home.FriendStatueAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuseumSculptorFragment : BaseFragment<FragmentMuseumSculptorBinding>(R.layout.fragment_museum_sculptor) {


    private lateinit var museumCommentRVAdapter: MuseumCommentRVAdapter

    val viewModel: MuseumDetailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MuseumDetailViewModel::class.java)
    }



    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        val call: Call<MuseumDetail> = ServicePool.museumService.getMuseumDetail(
            accessToken = "JSESSIONID="+LocalDataSource.getAccessToken().toString(),
            stoneId = viewModel.stoneid.value.toString()
        )

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<MuseumDetail> {
            override fun onResponse(call: Call<MuseumDetail>, response: Response<MuseumDetail>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data?.stone
                    if (data!=null){

                        binding.museumHabitString.text=data.name
                        binding.museumGoalDayStringInput.text=data.goal
                        binding.museumStartDayString.text=data.startDate
                        binding.museumCategoryText.text=data.category

                        binding.museumSculptorInclude2.museumIncludeCommentText.text=data.oneComment
                        binding.museumSculptorInclude2.museumGoalDayInclude.text=data.dDay
                        binding.museumSculptorInclude2.museumGoalRateInclude.text=data.achievementRate.toString()+"%"
                        binding.museumSculptorInclude2.museumGramInclude.text=data.powder.toString()+"g"
                        binding.museumSculptorInclude2.museumAllAchieveNum.text=data.achievementCounts.A
                        binding.museumSculptorInclude2.museumMiddleNum.text=data.achievementCounts.B
                        binding.museumSculptorInclude2.museumNotNum.text=data.achievementCounts.C


                    }

                    Log.d("박물관 서버",data.toString())

                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("박물관 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<MuseumDetail>, t: Throwable) {
                // 통신 실패 처리
                Log.d("박물관 서버",t.message.toString())
            }
        })

        /*val dumy : ArrayList<Comment> = ArrayList<Comment>()
        dumy.add(Comment(R.drawable.img_museum_sculptor_profile,"매트","너무 예쁘게 가꾸셨네요! 저도 따라 해봐야겠어요!!",true))
        dumy.add(Comment(R.drawable.img_museum_sculptor_profile,"클라라","저도 이렇게 하고 싶어요!",false))
        museumCommentRVAdapter= MuseumCommentRVAdapter(dumy)*/

        var itemList : List<Comment> =ArrayList<Comment>()
        // 서버 통신 요청
        val call2: Call<Comments> = ServicePool.museumService.getComments("JSESSIONID="+ LocalDataSource.getAccessToken().toString(), viewModel.stoneid.value.toString())
        call2.enqueue(object : Callback<Comments> {
            override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
                if (response.isSuccessful) {
                    var itemList = response.body()?.data?.comments
                    if (itemList != null) {
                        museumCommentRVAdapter.commentList=itemList
                    }
                    museumCommentRVAdapter.notifyDataSetChanged()

                    Log.d("박물관 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("박물관 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<Comments>, t: Throwable) {
                // 통신 실패 처리
                Log.d("박물관 서버",t.message.toString())
            }
        })
        museumCommentRVAdapter = MuseumCommentRVAdapter(itemList)
        binding.museumSculptorCommentRv.adapter=museumCommentRVAdapter
        binding.museumSculptorCommentRv.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        binding.museumNewComment.setOnClickListener{

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