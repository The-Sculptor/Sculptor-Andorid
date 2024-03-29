package com.umc.sculptor.ui.museum

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.MuseumDetail
import com.umc.sculptor.data.model.dto.MuseumDetailViewModel
import com.umc.sculptor.data.model.remote.home.MyRepresentStone
import com.umc.sculptor.data.model.remote.museum.Comment
import com.umc.sculptor.data.model.remote.museum.CommentResponse
import com.umc.sculptor.data.model.remote.museum.Comments
import com.umc.sculptor.data.model.remote.museum.RepresentResponseDto
import com.umc.sculptor.databinding.FragmentMuseumSculptorBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuseumSculptorFragment : BaseFragment<FragmentMuseumSculptorBinding>(R.layout.fragment_museum_sculptor) {


    private lateinit var museumCommentRVAdapter: MuseumCommentRVAdapter

    val viewModel: MuseumDetailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MuseumDetailViewModel::class.java)
    }

    private var isPresent : Boolean = false
    private var canComment : Boolean = false


    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        binding.addComment.etCommet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변화가 시작될 때
            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트에 변화가 있을 때
                if (!binding.addComment.etCommet.text.isNullOrEmpty()) {
                    canComment = true
                    binding.addComment.upload.setImageResource(R.drawable.comment_upload)
                } else {
                    canComment = false
                    binding.addComment.upload.setImageResource(R.drawable.upload_not)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변화가 끝났을 때
            }
        })

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
                        binding.museumStartDayStringInput.text=data.startDate

                        when(data.category){
                            "WORKOUT" -> {
                                binding.imgCategory.setImageResource(R.drawable.img_exercise_icon)
                                binding.museumCategoryText.text="운동"
                                binding.museumSculptorInclude.museumSculptorImg.setImageResource(R.drawable.stone_workoutimg)
                            }
                            "STUDY" -> {
                                binding.imgCategory.setImageResource(R.drawable.img_study_icon)
                                binding.museumSculptorInclude.museumSculptorImg.setImageResource(R.drawable.stone_studyimg)
                                binding.museumCategoryText.text="공부"
                            }
                            "DAILY" -> {
                                binding.imgCategory.setImageResource(R.drawable.img_routine_icon)
                                binding.museumSculptorInclude.museumSculptorImg.setImageResource(R.drawable.stone_dailyimg)
                                binding.museumCategoryText.text="일상"
                            }
                        }

                        if(data.isRepresent){
                            isPresent = true
                            binding.museumSculptorInclude.star.setBackgroundResource(R.drawable.star_fill)
                        }



                        binding.museumSculptorInclude2.museumIncludeCommentText.text=data.oneComment ?: ""
                        binding.museumSculptorInclude2.museumGoalDayInclude.text=data.dDay
                        binding.museumSculptorInclude2.museumGoalRateInclude.text=data.achievementRate.toString()+"%"
                        binding.museumSculptorInclude2.museumGramInclude.text=data.powder.toString()+"g"
                        binding.museumSculptorInclude2.museumAllAchieveNum.text=data.achievementCounts.A.toString()
                        binding.museumSculptorInclude2.museumMiddleNum.text=data.achievementCounts.B.toString()
                        binding.museumSculptorInclude2.museumNotNum.text=data.achievementCounts.C.toString()


                    }

                    Log.d("박물관 서버1",data.toString())

                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("박물관 서버1","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<MuseumDetail>, t: Throwable) {
                // 통신 실패 처리
                Log.d("박물관 서버1",t.message.toString())
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

                    Log.d("박물관 서버2",itemList.toString())
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

        binding.museumNewComment.setOnClickListener {
            binding.addComment.root.visibility = View.VISIBLE
        }

        binding.addComment.cancel.setOnClickListener {
            binding.addComment.root.visibility = View.GONE
        }

        binding.addComment.upload.setOnClickListener {
            if(canComment){
                // 서버 통신 요청
                val presentStoneCall: Call<CommentResponse> = ServicePool.museumService.comment(
                    "JSESSIONID="+LocalDataSource.getAccessToken().toString(),
                    viewModel.stoneid.value.toString(),
                    binding.addComment.etCommet.text.toString())

                // 비동기적으로 요청 수행
                presentStoneCall.enqueue(object : Callback<CommentResponse> {
                    override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                        if (response.isSuccessful) {
                            Log.d("박물관 comment 서버", response.body().toString())
                            binding.addComment.root.visibility = View.GONE
                            canComment = false
                            getComment()
                        } else {
                            // 서버에서 오류 응답을 받은 경우 처리
                            Log.d("박물관 comment 서버","서버통신 오류")
                        }
                    }

                    override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                        // 통신 실패 처리
                        Log.d("박물관 comment 서버",t.message.toString())
                    }
                })
            }
        }



        binding.museumSculptorInclude.star.setOnClickListener {
            if(!isPresent){
                // 서버 통신 요청
                val presentStoneCall: Call<RepresentResponseDto> = ServicePool.museumService.representStone("JSESSIONID="+LocalDataSource.getAccessToken().toString(), viewModel.stoneid.value.toString())

                // 비동기적으로 요청 수행
                presentStoneCall.enqueue(object : Callback<RepresentResponseDto> {
                    override fun onResponse(call: Call<RepresentResponseDto>, response: Response<RepresentResponseDto>) {
                        if (response.isSuccessful) {
                            binding.museumSculptorInclude.star.setBackgroundResource(R.drawable.star_fill)
                            Log.d("박물관 서버",response.body().toString())
                        } else {
                            // 서버에서 오류 응답을 받은 경우 처리
                            Log.d("박물관 서버","서버통신 오류")
                        }
                    }

                    override fun onFailure(call: Call<RepresentResponseDto>, t: Throwable) {
                        // 통신 실패 처리
                        Log.d("박물관 서버",t.message.toString())
                    }
                })
            }

        }


    }

    fun getComment(){
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

                    Log.d("박물관 서버2",itemList.toString())
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
    }
    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideIconAndShowBack(false)
    }

}