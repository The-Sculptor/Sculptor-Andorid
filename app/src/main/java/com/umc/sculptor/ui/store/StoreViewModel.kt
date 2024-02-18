package com.umc.sculptor.ui.store

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.data.model.remote.store.Stone

class StoreViewModel : ViewModel() {
    private val _selectedStatue = MutableLiveData<Stone>()// 선택된 아이템을 저장_ LiveData
    private val _selectedItem = MutableLiveData<Item>()
    val selectedStatue: LiveData<Stone> get() = _selectedStatue

    val selectedItem: LiveData<Item> get() = _selectedItem

    fun updateSelectedStatue(stone: Stone) {// 선택된 조각상 업데이트
        _selectedStatue.value = stone
        stone.isSelected = true
    }
    fun updatereleasedStatue(stone: Stone) {// 선택 안 된 조각상 업데이트
        _selectedStatue.value = stone
        stone.isSelected = false
    }

    fun updateSelectedItem_item(item:Item){//선택된 아이템(원석) 업데이트
        _selectedItem.value = item
        item.isSelected = true
        Log.d("viewModel", " ${item.itemName}-> ${item.isSelected}")
    }

    fun updatereleasedItem_item(item:Item) {// 선택된 조각상 업데이트
        _selectedItem.value = item
        item.isSelected = false
    }

}