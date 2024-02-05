package com.umc.sculptor.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Alarm
import com.umc.sculptor.databinding.FragmentAlarmBinding
import com.umc.sculptor.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var searchAdapter: SearchAdapter

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()


        val dumy : ArrayList<Alarm> =ArrayList<Alarm>()
        dumy.add(Alarm("SONG"))
        dumy.add(Alarm("NICKNAME"))

        searchAdapter = SearchAdapter(dumy)
        binding.rvSearchResult.adapter = searchAdapter
        binding.rvSearchResult.layoutManager = LinearLayoutManager(context)

    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.ivBack.setOnClickListener {
            (requireActivity() as AppCompatActivity).onBackPressed()
        }

    }
}