package com.umc.sculptor.ui.workshop.createStone

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.umc.sculptor.MainActivity
import com.umc.sculptor.R
import com.umc.sculptor.base.BaseFragment
import com.umc.sculptor.data.model.dto.Category
import com.umc.sculptor.data.model.dto.NewStoneViewModel
import com.umc.sculptor.databinding.FragmentCreateRockCategoryBinding
import com.umc.sculptor.databinding.FragmentCreateRockDateBinding
import com.umc.sculptor.databinding.FragmentHomeBinding
import com.umc.sculptor.databinding.FragmentWorkshopBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.concurrent.TimeUnit
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initDataBinding() {
        super.initDataBinding()

        // 현재 날짜 가져오기
        val currentDate = LocalDate.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val formattedDate = currentDate.format(formatter)

        binding.etStoneDate.text = "시작일   |   "+formattedDate


        binding.tvStoneName.text = viewModel.getNewStone().value?.name ?: "null"
        when(viewModel.getNewStone().value?.category){
            Category.WORKOUT ->{
                binding.tvCategory.text = "운동"
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.material_symbols_exercise_outline)
                binding.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
            }
            Category.STUDY -> {
                binding.tvCategory.text = "공부"
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.mdi_book_open_blank_variant_outline)
                binding.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
            }
            Category.DAILY -> {
                binding.tvCategory.text = "일상"
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.lucide_lamp)
                binding.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
            }
            else -> binding.tvCategory.text = ""
        }


    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.etStoneDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            context?.let { it1 ->
                DatePickerDialog(it1, { _, year, month, day ->
                    run {
                        binding.etStoneDate.setText("시작일   |   "+year.toString() + "." + String.format("%02d", month + 1) + "." + String.format("%02d", day))

                        // 현재 날짜 가져오기
                        val currentDate = LocalDate.now()

                        // 특정 날짜 설정 (YYYY-MM-DD 형식)
                        val specificDate = LocalDate.of(year, month+1, day)

                        // 두 날짜 간의 차이 계산
                        var daysUntilSpecificDate = ChronoUnit.DAYS.between(currentDate, specificDate)
                        daysUntilSpecificDate = 66 +daysUntilSpecificDate
                        daysUntilSpecificDate = -daysUntilSpecificDate

                        viewModel.setDDay(daysUntilSpecificDate.toString())
                    }
                }, year, month, day)
            }?.show()

        }

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