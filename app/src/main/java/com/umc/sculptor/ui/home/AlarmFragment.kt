package com.umc.sculptor.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Alarm
import com.umc.sculptor.data.model.dto.FriendStatue
import com.umc.sculptor.databinding.FragmentAlarmBinding
import com.umc.sculptor.databinding.FragmentMypageBinding

class AlarmFragment : BaseFragment<FragmentAlarmBinding>(R.layout.fragment_alarm) {

    private lateinit var alarmAdapter: AlarmAdapter

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()


        val dumy : ArrayList<Alarm> =ArrayList<Alarm>()
        dumy.add(Alarm("박준형님이 회원님을 팔로잉하기 시작했습니다."))
        dumy.add(Alarm("조깅 ㅣ 돌에 이끼가 끼기 시작했습니다. 조각을 다시 시작해 돌에 낀 이끼를 없애주세요!"))
        dumy.add(Alarm("조깅 ㅣ 돌에 이끼가 끼기 시작했습니다. 조각을 다시 시작해 돌에 낀 이끼를 없애주세요!"))

        alarmAdapter = AlarmAdapter(dumy)
        binding.rvAlarm.adapter = alarmAdapter
        binding.rvAlarm.layoutManager = LinearLayoutManager(context)

    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.ivBack.setOnClickListener {
            (requireActivity() as AppCompatActivity).onBackPressed()
        }

    }
}