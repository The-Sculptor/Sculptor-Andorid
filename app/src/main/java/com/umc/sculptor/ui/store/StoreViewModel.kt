package com.umc.sculptor.ui.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoreViewModel : ViewModel() {
    private val _selectedStatue = MutableLiveData<Item>()// 선택된 아이템을 저장_ LiveData
    private val _selectedItem = MutableLiveData<Item_WB>()
    val selectedStatue: LiveData<Item> get() = _selectedStatue

    val selectedItem: LiveData<Item_WB> get() = _selectedItem

    fun updateSelectedItem(item: Item) {// 선택된 아이템 업데이트
        _selectedStatue.value = item
    }

    fun updateSelectedItem_item(item:Item_WB){
        _selectedItem.value = item
    }

}