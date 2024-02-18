package com.umc.sculptor.ui.museum

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.remote.museum.Data
import com.umc.sculptor.data.model.remote.museum.Museum
import com.umc.sculptor.data.model.remote.museum.Stone
import com.umc.sculptor.databinding.FragmentMuseumProfileMeBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuseumProfileMeFragment : BaseFragment<FragmentMuseumProfileMeBinding>(R.layout.fragment_museum_profile_me) {

    private lateinit var museumSculptorRVAdapter: MuseumSculptorAdapter
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(false)
        (activity as MainActivity).hideIconAndShowBack(false)


    }

    override fun initDataBinding() {
        super.initDataBinding()
        /*val dumy : ArrayList<Sculptor> = ArrayList<Sculptor>()
        dumy.add(Sculptor("D+66","조깅하기","2023.06.13",R.drawable.img_museum_sculptor))
        dumy.add(Sculptor("D+33","아침먹기","2023.03.03",R.drawable.img_museum_sculptor))
        dumy.add(Sculptor("D+12","일기쓰기","2023.10.20",R.drawable.img_museum_sculptor))*/
        var itemList : List<Stone> =ArrayList<Stone>()
        // 서버 통신 요청
        val call: Call<Data> = ServicePool.museumService.getMuseum("JSESSIONID="+ LocalDataSource.getAccessToken().toString(), "48cc649d-74cf-4e1d-9bb6-0e1b36857c37")

        // 비동기적으로 요청 수행
        call.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful) {
                    itemList = response.body()?.stones?: ArrayList<Stone>()
                    museumSculptorRVAdapter.sculptorList=itemList
                    museumSculptorRVAdapter.notifyDataSetChanged()

                    Log.d("박물관 서버",itemList.toString())
                } else {
                    // 서버에서 오류 응답을 받은 경우 처리
                    Log.d("박물관 서버","서버통신 오류")
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                // 통신 실패 처리
                Log.d("박물관 서버",t.message.toString())
            }
        })

        museumSculptorRVAdapter = MuseumSculptorAdapter(itemList)
        binding.museumProfileSculptorRv.adapter = museumSculptorRVAdapter
        binding.museumProfileSculptorRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)



        museumSculptorRVAdapter.setMyItemClickListener(object: MuseumSculptorAdapter.MyItemClickListener{
            override fun onItemClick(position:Int) {
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