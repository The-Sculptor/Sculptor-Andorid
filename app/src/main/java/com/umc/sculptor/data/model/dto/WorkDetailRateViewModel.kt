package com.umc.sculptor.data.model.dto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// ViewModel 클래스
class WorkDetailRateViewModel : ViewModel() {
    val id = MutableLiveData<String>()
}