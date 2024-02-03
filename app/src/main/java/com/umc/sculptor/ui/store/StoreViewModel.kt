package com.umc.sculptor.ui.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoreViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<Item>()// 선택된 아이템을 저장_ LiveData
    val selectedItem: LiveData<Item> get() = _selectedItem

    fun updateSelectedItem(item: Item) {// 선택된 아이템 업데이트
        _selectedItem.value = item
    }
}