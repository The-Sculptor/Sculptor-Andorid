package com.umc.sculptor.data.model.dto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// ViewModel 클래스
class FriendMuseumViewModel : ViewModel() {
    val message = MutableLiveData<String>()
}