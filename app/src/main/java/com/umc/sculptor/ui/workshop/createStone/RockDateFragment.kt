package com.umc.sculptor.ui.workshop.createStone

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.databinding.FragmentCreateRockCategoryBinding
import com.umc.sculptor.databinding.FragmentCreateRockDateBinding
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.databinding.FragmentWorkshopBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern

class RockDateFragment : BaseFragment<FragmentCreateRockDateBinding>(R.layout.fragment_create_rock_date) {
    val viewModel: NewStoneViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NewStoneViewModel::class.java)
    }

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).hideBottomNav(true)
        (activity as MainActivity).hideIconAndShowBack(true)
    }

    override fun initDataBinding() {
        super.initDataBinding()

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun initAfterBinding() {
        super.initAfterBinding()

//        binding.etStoneDate.setOnClickListener {
//
//        }

        binding.btnComplete.setOnClickListener {
            viewModel.setNewStoneDate(getDate(binding.etStoneDate.text.toString()))
            navController.navigate(R.id.action_rockDateFragment_to_rockCompleteFragment)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDate(text: String) : String {

        // 정규식을 사용하여 날짜 문자열 추출
        val pattern = Pattern.compile("\\d{4}\\.\\d{2}\\.\\d{2}")
        val matcher = pattern.matcher(text)
        var dateString = ""
        if (matcher.find()) {
            dateString = matcher.group()
        }

        // 추출한 날짜 문자열을 LocalDate로 변환
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val localDate = LocalDate.parse(dateString, dateFormatter)

        // LocalDate를 원하는 형식의 문자열로 변환
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val formattedDate = localDate.atStartOfDay().format(outputFormatter)

        return formattedDate
    }

}