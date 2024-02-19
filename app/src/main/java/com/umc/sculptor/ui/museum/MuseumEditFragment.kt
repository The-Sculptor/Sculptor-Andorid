package com.umc.sculptor.ui.museum

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.remote.home.MyPageResonseDto
import com.umc.sculptor.data.model.remote.login.LogoutDto
import com.umc.sculptor.data.model.remote.museum.EditProfileDto
import com.umc.sculptor.data.model.remote.museum.EditReqeustDto
import com.umc.sculptor.data.model.remote.museum.EditStonesDto
import com.umc.sculptor.data.model.remote.museum.EditUserDto
import com.umc.sculptor.data.model.remote.museum.StoneX
import com.umc.sculptor.databinding.FragmentMuseumEditBinding
import com.umc.sculptor.login.LocalDataSource
import com.umc.sculptor.ui.home.FriendStatueAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuseumEditFragment : BaseFragment<FragmentMuseumEditBinding>(R.layout.fragment_museum_edit){

    private lateinit var museumEditRVAdapter: MuseumEditRVAdapter
    private var edit: Boolean = false
    private val deleteStones : ArrayList<String> = ArrayList()
    override fun initStartView() {
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)

        val call: Call<EditProfileDto> = ServicePool.museumService.getUser("JSESSIONID="+ LocalDataSource.getAccessToken().toString())

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<EditProfileDto> {
            override fun onResponse(call: Call<EditProfileDto>, response: Response<EditProfileDto>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    context?.let {
                        Glide.with(it)
                            .load(data?.profileImage)
                            .placeholder(R.drawable.ellipse) // 이미지 로딩 중에 표시될 placeholder 이미지
                            .error(R.drawable.ellipse) // 이미지 로딩 실패 시 표시될 이미지
                            .into(binding.museumEditProfileImg)
                    }
                    binding.museumEditProfileNicknameBox.text = Editable.Factory.getInstance().newEditable(data?.nickname)
                    binding.museumEditProfileIntroBox.text = Editable.Factory.getInstance().newEditable(data?.introduction)

                    // 데이터 매핑 후 버튼 색상 변경
                    binding.btnComplete.setTextColor(ContextCompat.getColor(context!!, R.color.gray_908F90))
                    binding.btnComplete.setBackgroundColor(ContextCompat.getColor(context!!,R.color.gray_555555))
                    edit =false
                    Log.d("프로필편집 서버",data.toString())

                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("프로필편집 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<EditProfileDto>, t: Throwable) {
                // 통신 실패 처리
                Log.d("프로필편집 서버",t.message.toString())
            }
        })

    }

    override fun initDataBinding() {
        super.initDataBinding()
        val dumy: ArrayList<StoneX> = ArrayList<StoneX>()
//        dumy.add(Edit("D+66","조깅하기"))
//        dumy.add(Edit("D+33","아침먹기"))
//        dumy.add(Edit("D+12","일기쓰기"))

        val call: Call<EditStonesDto> = ServicePool.museumService.getStones(
            "JSESSIONID=" + LocalDataSource.getAccessToken().toString()
        )

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<EditStonesDto> {
            override fun onResponse(call: Call<EditStonesDto>, response: Response<EditStonesDto>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data?.stones
                    museumEditRVAdapter.editList = data as ArrayList<StoneX>
                    museumEditRVAdapter.notifyDataSetChanged()
                    Log.d("프로필편집 서버", data.toString())

                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("프로필편집 서버", "서버통신 오류")
                }
            }

            override fun onFailure(call: Call<EditStonesDto>, t: Throwable) {
                // 통신 실패 처리
                Log.d("프로필편집 서버", t.message.toString())
            }
        })

        museumEditRVAdapter = MuseumEditRVAdapter(dumy)
        binding.museumSculptorCommentRv.adapter = museumEditRVAdapter
        binding.museumSculptorCommentRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        // 아이템 클릭 리스너 설정
        museumEditRVAdapter.setOnItemClickListener(object :
            MuseumEditRVAdapter.OnItemClickListener {
            override fun onItemClick(id: String) {
                edit = true
                binding.btnComplete.setTextColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.black_2B2B2F
                    )
                )
                binding.btnComplete.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.gray_EFEFEF
                    )
                )
                deleteStones.add(id)
            }

        })

        binding.museumEditProfileIntroBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변화가 시작될 때
            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트에 변화가 있을 때
                binding.btnComplete.setTextColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.black_2B2B2F
                    )
                )
                binding.btnComplete.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.gray_EFEFEF
                    )
                )
                edit = true
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변화가 끝났을 때
            }
        })

        binding.museumEditProfileNicknameBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변화가 시작될 때
            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트에 변화가 있을 때
                binding.btnComplete.setTextColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.black_2B2B2F
                    )
                )
                binding.btnComplete.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.gray_EFEFEF
                    )
                )
                edit = true
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변화가 끝났을 때
            }
        })

        binding.btnComplete.setOnClickListener {
            if (edit) {
                val reqeustDto = EditReqeustDto(
                    binding.museumEditProfileNicknameBox.text.toString(),
                    null,
                    binding.museumEditProfileIntroBox.text.toString()
                )
                val call: Call<EditUserDto> = ServicePool.museumService.editProfile(
                    "JSESSIONID=" + LocalDataSource.getAccessToken().toString(), reqeustDto
                )

                // 비동기적으로 요청 수행
                call.enqueue(object : Callback<EditUserDto> {
                    override fun onResponse(
                        call: Call<EditUserDto>,
                        response: Response<EditUserDto>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("프로필편집 서버", response.toString())
                            deleteStones.forEach {id ->
                                val size = deleteStones.size
                                if (id == deleteStones.get(size-1)){
                                    deleteStone(id, true)
                                }else
                                    deleteStone(id, false)
                            }

                        } else {
                            // 서버에서 오류 응답을 받은 경우 처리
                            Log.d("프로필편집 서버", "서버통신 오류")
                        }
                    }

                    override fun onFailure(call: Call<EditUserDto>, t: Throwable) {
                        // 통신 실패 처리
                        Log.d("프로필편집 서버", t.message.toString())
                    }
                })

            }
        }
    }


    private fun deleteStone(id:String, last: Boolean){
        val call2: Call<LogoutDto> = ServicePool.museumService.deleteStone("JSESSIONID="+ LocalDataSource.getAccessToken().toString(), id)

        // 비동기적으로 요청 수행
        call2.enqueue(object : Callback<LogoutDto> {
            override fun onResponse(call: Call<LogoutDto>, response: Response<LogoutDto>) {
                if (response.isSuccessful) {
                    Log.d("프로필편집 서버",response.toString())
                    if(last)
                        (activity as MainActivity).onBackPressed()
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("프로필편집 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<LogoutDto>, t: Throwable) {
                // 통신 실패 처리
                Log.d("프로필편집 서버",t.message.toString())
            }
        })
    }
}
